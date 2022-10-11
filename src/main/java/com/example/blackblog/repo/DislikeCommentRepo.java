package com.example.blackblog.repo;

import com.example.blackblog.entity.DislikeComment;
import org.springframework.data.repository.CrudRepository;

public interface DislikeCommentRepo extends CrudRepository<DislikeComment, Long> {
}
