package com.safnaliftoff.upscaile.models.data;

import com.safnaliftoff.upscaile.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}