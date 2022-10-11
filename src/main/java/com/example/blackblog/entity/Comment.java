package com.example.blackblog.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @OneToMany
    private List<LikeComment> likedComment;

    @OneToMany
    private List<DislikeComment> dislikedComment;

    @ManyToMany
    @JoinTable(name = "reply_comment",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "reply_id"))
    private List<ReplyComment> replyComment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment() {
    }

    public Comment(String text, List<LikeComment> likedComment, List<DislikeComment> dislikedComment, List<ReplyComment> replyComments, User user) {
        this.text = text;
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

    public List<ReplyComment> getReplyComment() {
        return replyComment;
    }

    public void setReplyComment(List<ReplyComment> replyComment) {
        this.replyComment = replyComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}