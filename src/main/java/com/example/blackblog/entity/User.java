package com.example.blackblog.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String role;

    @ManyToMany
    private List<Comment> comment;

    @OneToMany(mappedBy = "user")
    private List<LikeComment> likedComent;

    @OneToMany(mappedBy = "user")
    private List<DislikeComment> dislikedComent;
}