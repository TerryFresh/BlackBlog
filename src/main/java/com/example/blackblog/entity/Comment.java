package com.example.blackblog.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="reply_id")
    private Comment reply;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "reply",
            fetch = FetchType.LAZY)
    private List<Comment> comments;
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "comment",
            fetch = FetchType.LAZY)
    private List<Reaction> reactions;

    public Comment() {
    }

    public Comment(String text, User user, Comment reply) {
        this.text = text;
        this.user = user;
        this.reply = reply;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getReply() {
        return reply;
    }

    public void setReply(Comment reply) {
        this.reply = reply;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }
}