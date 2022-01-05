package com.safnaliftoff.upscaile.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubmittedImageDTO {

    @NotNull
    @NotBlank
    private String username;

    public String getUsername() {

        return username;
    }



}
