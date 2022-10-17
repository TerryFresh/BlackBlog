package com.example.blackblog.repo;

import com.example.blackblog.entity.Reaction;
import com.example.blackblog.entity.User;
import com.example.blackblog.enums.ReactionsType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReactionRepo extends CrudRepository<Reaction, Long> {
    List<Reaction> findByReactionType(ReactionsType text);

    List<Reaction> findByCommentId(Long userId);

    List<Reaction> findByUserId(Long userId);

}
