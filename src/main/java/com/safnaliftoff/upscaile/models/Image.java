package com.safnaliftoff.upscaile.models;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Image extends AbstractEntity {

    @Lob
    private byte[] content;

    private String name;

    private String location;
    @ManyToOne
    private  User user;


    public Image() {
    }

    public Image(String name, String location, User user) {
        this.name = name;
        this.location = location;
        this.user = user;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}