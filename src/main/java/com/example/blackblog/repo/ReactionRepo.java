package com.example.blackblog.repo;

import com.example.blackblog.entity.Comment;
import com.example.blackblog.entity.Reaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReactionRepo extends CrudRepository<Reaction, Long> {
    List<Reaction> findByReactionType(String text);
}
