package com.example.blackblog.repo;

import com.example.blackblog.entity.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentsRepo extends CrudRepository<Comment, Long> {
    List<Comment> findByComment(String comment);

}