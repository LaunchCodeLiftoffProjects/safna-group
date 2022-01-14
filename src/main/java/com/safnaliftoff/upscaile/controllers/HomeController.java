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
            Path output_path = Paths.get(userDir + "/upscaling/processed_images/" + filename);

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
                    output_path.toAbsolutePath().toString(), input_path.toAbsolutePath().toString()));
            Path temp = Files.move(input_path, Paths.get(userDir + "/upscaling/original_images/" + filename));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

}