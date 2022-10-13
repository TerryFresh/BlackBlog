package com.example.blackblog.repo;

import com.example.blackblog.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepo extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}