package com.safnaliftoff.upscaile.controllers;

import com.safnaliftoff.upscaile.data.ImageRepository;
import com.safnaliftoff.upscaile.data.UserRepository;
import com.safnaliftoff.upscaile.models.User;
import com.safnaliftoff.upscaile.services.FileLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private FileLocationService fileLocationService;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationController authenticationController;

    @GetMapping
    public String main(Model model) {   model.addAttribute("images",imageRepository.findAll());return "index";}

    @PostMapping
    public String uploadImage(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) throws Exception {
      User currentUser=  authenticationController.getUserFromSession(request.getSession());


//        System.out.println(authenticationController.getUserFromSession(request.getSession()));
        fileLocationService.save(file.getBytes(), file.getOriginalFilename(),currentUser);
//        model.addAttribute("images",imageRepository.findAll());
        return "redirect:/";
    }

}