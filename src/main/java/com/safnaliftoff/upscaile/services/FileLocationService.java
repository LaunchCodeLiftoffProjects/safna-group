package com.safnaliftoff.upscaile.services;


import com.safnaliftoff.upscaile.models.Image;
import com.safnaliftoff.upscaile.models.data.FileSystemRepository;
import com.safnaliftoff.upscaile.models.data.ImageDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class FileLocationService {
    @Autowired
    FileSystemRepository fileSystemRepository;
    @Autowired
    ImageDbRepository imageDbRepository;



   public  Long save(byte[] bytes, String imageName) throws Exception {
        String location = fileSystemRepository.save(bytes, imageName);

        return imageDbRepository.save(new Image(imageName, location))
                .getId();
        }
   public FileSystemResource find (Long imageId){

        Image image = imageDbRepository.findById(imageId.intValue())

                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            return fileSystemRepository.findInFileSystem(image.getLocation());
        }

    }
