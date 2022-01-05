package com.safnaliftoff.upscaile.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String username;


    @NotNull
    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}
    //Setting up one to many relationship
    @OneToMany()
    @JoinColumn(name= "user_id")
    private List<Image> images = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getUsername() {
        return username;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

}
