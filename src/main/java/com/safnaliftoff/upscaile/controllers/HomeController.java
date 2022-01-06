package com.safnaliftoff.upscaile.controllers;

import com.safnaliftoff.upscaile.data.ImageRepository;
import com.safnaliftoff.upscaile.data.UserRepository;
import com.safnaliftoff.upscaile.models.Image;
import com.safnaliftoff.upscaile.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

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
    public String main() { return "index"; }

    @PostMapping
    public String uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {

        User currentUser=  authenticationController.getUserFromSession(request.getSession());

        try {
            Runtime runTime = Runtime.getRuntime();
            Path input_path = Paths.get("realesrgan-ncnn-vulkan-v0.1.3.2\\img_in\\" + file.getOriginalFilename()).toAbsolutePath();
            Files.createDirectories(input_path.getParent());
            Files.write(input_path, file.getBytes());

            Path output_path = Paths.get(HomeController.class.getResource("/").getPath().substring(1) + new Date().getTime() + "-" + file.getOriginalFilename());
//            System.out.println(input_path.toString());
//            System.out.println(output_path.toAbsolutePath().toString().replace("\\", "\\\\"));
            String[] params = new String[] {
                    "realesrgan-ncnn-vulkan-v0.1.3.2\\windows\\realesrgan-ncnn-vulkan-v0.1.3.2-windows\\realesrgan-ncnn-vulkan.exe",
                    "-i", "realesrgan-ncnn-vulkan-v0.1.3.2\\img_in\\" + file.getOriginalFilename(),
                    "-o", output_path.toAbsolutePath().toString().replace("\\", "\\\\")
            };

            ProcessBuilder pb = new ProcessBuilder(params);
            Process process = pb.start();
            process.waitFor();

            imageRepository.save(new Image(file.getOriginalFilename(), output_path.toAbsolutePath().toString(), currentUser));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

}