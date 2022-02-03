# Upsc[AI]le

Upscaile is a web application for upscaling low resolution images using the latest AI super resolution methods. 
The app allows users to upload an image and within a matter of seconds, download a clearer, more detailed version of it. 
Users must register an account to access their processed images from the website.

## Features
- [x] User account creation and login authentication 
- [x] Upload images 
- [x] Upscale low resolution images into high resolution images
- [x] Save upscaled images in database 
- [x] View upscaled images
- [x] Delete saved images

## Technologies
Java, Spring Boot, MySQL, Hibernate, Thymeleaf templates, RealEsrgan, Bootstrap

## How to Set Up the Project
1. Clone repo
2. Download "RealESRGAN_x4plus" file from [here](https://github.com/xinntao/Real-ESRGAN/blob/master/docs/model_zoo.md)
   - Place file in "upscaling/models" folder in project directory
3. Run UpscaileApplication
4. Navigate to http://localhost:8080/ in a web browser