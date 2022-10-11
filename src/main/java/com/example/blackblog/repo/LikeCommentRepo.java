package com.example.blackblog.repo;

import com.example.blackblog.entity.LikeComment;
import org.springframework.data.repository.CrudRepository;

public interface LikeCommentRepo extends CrudRepository<LikeComment, Long> {
}
