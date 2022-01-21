package com.safnaliftoff.upscaile.controllers;

import com.safnaliftoff.upscaile.data.ImageRepository;
import com.safnaliftoff.upscaile.data.UserRepository;
import com.safnaliftoff.upscaile.models.Image;
import com.safnaliftoff.upscaile.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationController authenticationController;

    @GetMapping
    public String main(Model model, HttpServletRequest request) {
        User currentUser=  authenticationController.getUserFromSession(request.getSession());
        List<Image> userImages = currentUser.getImages();
        model.addAttribute("images", userImages);
        System.out.println(userImages);

        return "index";
    }
    @GetMapping("/deleteImage")
    public  String deleteImage(@RequestParam int imageId) {
        imageRepository.deleteById(imageId);
        return "redirect:/";

    }

    @PostMapping
    public String uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {

        User currentUser=  authenticationController.getUserFromSession(request.getSession());
        String userDir = System.getProperty("user.dir");

        try {
            // Save uploaded file to input folder with current time appended to original filename
            String filename = new Date().getTime() + "-" + file.getOriginalFilename();
            Path input_path = Paths.get(userDir + "/upscaling/input/" + filename);
            Files.createDirectories(input_path.getParent());
            Files.createDirectories(Paths.get(userDir + "/upscaling/original_images/" + filename).getParent());
            Files.createDirectories(Paths.get(userDir + "/upscaling/processed_images/" + filename).getParent());
            Files.write(input_path, file.getBytes());

            //
            Path lo_path = Paths.get("/upscaling/original_images/" + filename);
            Path hi_path = Paths.get("/upscaling/processed_images/" + filename.substring(0, filename.length()-3) + "png");

            ProcessBuilder pb = new ProcessBuilder();
            String[] params;
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                params = new String[] {
                        "./upscaling/realesrgan-ncnn-vulkan.exe",
                        "-i", "upscaling/input",
                        "-o", "upscaling/processed_images"
                };
            } else {
                params = new String[] {
                        "./realesrgan-ncnn-vulkan",
                        "-i", "input",
                        "-o", "processed_images"
                };
                pb.directory(new File(userDir + "/upscaling"));
            }

            pb.command(params);
            Process process = pb.start();
            process.waitFor();

            imageRepository.save(new Image(currentUser, filename,
                    hi_path.toString(), lo_path.toString()));
            Path temp = Files.move(input_path, Paths.get(userDir + "/upscaling/original_images/" + filename));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

}