package com.example.blackblog.entity;

import com.example.blackblog.entity.Comment;
import com.example.blackblog.entity.User;

import javax.persistence.*;

@Entity
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reactionType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public Reaction() {
    }

    public Reaction(String reactionType, User user, Comment comment) {
        this.reactionType = reactionType;
        this.user = user;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReactionType() {
        return reactionType;
    }

    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Reaction{" +
                "id=" + id +
                ", reactionType='" + reactionType + '\'' +
                ", userId='" + user + '\'' +
                ", comment=" + comment +
                '}';
    }
}
