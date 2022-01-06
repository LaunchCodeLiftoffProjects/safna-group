package com.safnaliftoff.upscaile.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Image extends AbstractEntity {

    private String name;

    private String location;

    @ManyToOne
    private User user;

    public Image() {
    }

    public Image(String name, String location, User user) {
        this.name = name;
        this.location = location;
        this.user = user;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

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