package com.example.blackblog.repo;

import com.example.blackblog.entity.Comment;
import com.example.blackblog.entity.Reaction;
import com.example.blackblog.entity.User;
import com.example.blackblog.enums.ReactionEnable;
import com.example.blackblog.enums.ReactionsType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ReactionRepo extends CrudRepository<Reaction, Long> {
    List<Reaction> findByReactionType(ReactionsType text);

    List<Reaction> findByCommentId(Long commentId);

    List<Reaction> findByUserId(Long userId);

    List<Reaction> findByIsEnable(ReactionEnable isEnable);

    List<Reaction> findByUserAndCommentAndIsEnable(User user, Comment comment, ReactionEnable isEnable);

}
