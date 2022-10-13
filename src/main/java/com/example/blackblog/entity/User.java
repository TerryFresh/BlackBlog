package com.example.blackblog.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String role;

    private int enabled;

    @OneToMany(mappedBy = "user")
    private List<Comment> comment;

    @OneToMany(mappedBy = "user")
    private List<LikeComment> likedComent;

    @OneToMany(mappedBy = "user")
    private List<DislikeComment> dislikedComent;

    public User() {
    }

    public User(String username, String password, String role, int enabled, List<Comment> comment, List<LikeComment> likedComent, List<DislikeComment> dislikedComent) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
        this.comment = comment;
        this.likedComent = likedComent;
        this.dislikedComent = dislikedComent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public List<LikeComment> getLikedComent() {
        return likedComent;
    }

    public void setLikedComent(List<LikeComment> likedComent) {
        this.likedComent = likedComent;
    }

    public List<DislikeComment> getDislikedComent() {
        return dislikedComent;
    }

    public void setDislikedComent(List<DislikeComment> dislikedComent) {
        this.dislikedComent = dislikedComent;
    }

    @Override
    public String toString() {
        return "username: " + username;
    }
}