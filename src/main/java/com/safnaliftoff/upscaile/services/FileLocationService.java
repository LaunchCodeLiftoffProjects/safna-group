package com.safnaliftoff.upscaile.services;

import com.safnaliftoff.upscaile.data.FileSystemRepository;
import com.safnaliftoff.upscaile.data.ImageRepository;
import com.safnaliftoff.upscaile.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileLocationService {

    @Autowired
    FileSystemRepository fileSystemRepository;
    @Autowired
    ImageRepository imageRepository;

    public Integer save(byte[] bytes, String imageName) throws Exception {
        String location = fileSystemRepository.save(bytes, imageName);

        return imageRepository.save(new Image(imageName, location)).getId();

    }

}