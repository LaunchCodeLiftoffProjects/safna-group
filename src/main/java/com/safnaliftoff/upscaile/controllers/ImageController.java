package com.safnaliftoff.upscaile.controllers;

import com.safnaliftoff.upscaile.data.ImageRepository;
import com.safnaliftoff.upscaile.dto.LoginFormDTO;
import com.safnaliftoff.upscaile.models.Image;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.crypto.Data;


@RestController
    public class ImageController {
 private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//        @RequestMapping(value = "/")
//        public String index(Model model){
//            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//            String username = auth.getName(); // Get the username
//// here you connect to your database and pull out the relevant entity object
//            Data data = ImageRepository.findByUsername(username);
//// now pull the person object and allocate it to a new object
//            Image image = data.getImage();
//// do your transformations or queries on the person object
//// or
//// pass it to your view layer via
//            model.addAttribute("image", image);
//            model.addAttribute("username", username);
//            return "index";
//        }


//        @Autowired
//        ImageRepository imageRepository;
//        private String username;
//        String user = username;
//
//        @PostMapping
//        Long uploadImage(@RequestParam MultipartFile multipartImage) throws Exception {
//            Image dbImage = new Image();
//            dbImage.setName(multipartImage.getName());
//            dbImage.setContent(multipartImage.getBytes());
//
//            return (long) imageRepository.save(dbImage)
//                    .getId();
//        }
        //    //To store the uploaded file we can use a MultipartFile variable. We can retrieve this variable from the request parameter inside our controller's method:
        @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
        public String submit(
//                @RequestParam("file") MultipartFile file, ModelMap modelMap
        ) {
//            modelMap.addAttribute("file", file);
//            System.out.println("hello");
//
            System.out.println("This username is from image controller "+userName);
            return "fileUploadView";
        }
//
//        @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
//        Resource downloadImage(@PathVariable Long imageId) {
//            byte[] image = imageRepository.findById((Integer) Math.toIntExact(imageId))
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
//                    .getContent();
//
//            return new ByteArrayResource(image);
//        }
//
    }
//
