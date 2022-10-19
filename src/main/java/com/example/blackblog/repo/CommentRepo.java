package com.example.blackblog.repo;

import com.example.blackblog.entity.Comment;
import com.example.blackblog.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepo extends CrudRepository<Comment, Long> {
    List<Comment> findByReply(Comment reply);

    List<Comment> findByReplyAndIdAndUser(Comment reply, Long id, User user);

    List<Comment> findByUserId(Long userId);

}