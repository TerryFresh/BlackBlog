package com.example.blackblog.repo;

import com.example.blackblog.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {
    List<User> findByUsername(String username);
}