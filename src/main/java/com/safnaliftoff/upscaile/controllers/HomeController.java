package com.safnaliftoff.upscaile.controllers;

import com.safnaliftoff.upscaile.data.ImageRepository;
import com.safnaliftoff.upscaile.services.FileLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private FileLocationService fileLocationService;

    @GetMapping
    public String main() { return "index"; }

    @PostMapping
    public String uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        fileLocationService.save(file.getBytes(), file.getOriginalFilename());
        return "redirect:/";
    }

}