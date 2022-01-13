package com.safnaliftoff.upscaile.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Image extends AbstractEntity {

    private String name;

    private String locationHi;

    private String locationLo;

    @ManyToOne
    private User user;

    public Image() {
    }

    public Image(User user, String name, String locationHi, String locationLo) {
        this.user = user;
        this.name = name;
        this.locationHi = locationHi;
        this.locationLo = locationLo;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationHi() {
        return locationHi;
    }

    public void setLocationHi(String location) {
        this.locationHi = location;
    }

    public String getLocationLo() {
        return locationLo;
    }

    public void setLocationLo(String location) {
        this.locationLo = location;
    }

}