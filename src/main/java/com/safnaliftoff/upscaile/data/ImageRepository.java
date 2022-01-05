package com.safnaliftoff.upscaile.data;

import com.safnaliftoff.upscaile.models.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {

}