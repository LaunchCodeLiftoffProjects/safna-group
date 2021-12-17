package com.safnaliftoff.upscaile.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Image extends AbstractEntity{
//    public Long id;

    private String name;

    private String location;

    public Image() {
    }


    public Image(String name, String location) {
        this.name = name;
        this.location = location;
    }


//    public void setId(Integer id) {
//        this.id = id;
//    }

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
