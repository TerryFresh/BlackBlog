package com.example.blackblog.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    private Comment reply;

    @OneToMany(mappedBy = "user")
    private List<LikeComment> likedComment;

    @OneToMany(mappedBy = "user")
    private List<DislikeComment> dislikedComment;

    @OneToMany(mappedBy = "reply")
    private List<Comment> replyComment;

    @ManyToOne
    private User user;

    public Comment() {
    }

    public Comment(String text) {
        this.text = text;
    }

    public Comment(String text, Comment reply, List<LikeComment> likedComment, List<DislikeComment> dislikedComment, List<Comment> replyComment, User user) {
        this.text = text;
        this.reply = reply;
        this.likedComment = likedComment;
        this.dislikedComment = dislikedComment;
        this.replyComment = replyComment;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Comment getReply() {
        return reply;
    }

    public void setReply(Comment reply) {
        this.reply = reply;
    }

    public List<LikeComment> getLikedComment() {
        return likedComment;
    }

    public void setLikedComment(List<LikeComment> likedComment) {
        this.likedComment = likedComment;
    }

    public List<DislikeComment> getDislikedComment() {
        return dislikedComment;
    }

    public void setDislikedComment(List<DislikeComment> dislikedComment) {
        this.dislikedComment = dislikedComment;
    }

    public List<Comment> getReplyComment() {
        return replyComment;
    }

    public void setReplyComment(List<Comment> replyComment) {
        this.replyComment = replyComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{Comment:" +
                "text=" + text + '\'' +
                ", reply=" + reply +
                ", user=" + user + "} ";
    }
}