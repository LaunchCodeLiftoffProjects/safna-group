package com.safnaliftoff.upscaile.models.data;

import com.safnaliftoff.upscaile.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



public interface ImageDbRepository extends CrudRepository<Image, Integer> {

}
