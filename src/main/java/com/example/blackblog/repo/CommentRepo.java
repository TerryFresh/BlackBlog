package com.example.blackblog.repo;

import com.example.blackblog.entity.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepo extends CrudRepository<Comment, Long> {
    List<Comment> findByText(String text);

}