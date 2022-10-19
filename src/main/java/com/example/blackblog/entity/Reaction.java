package com.example.blackblog.entity;

import com.example.blackblog.enums.ReactionEnable;
import com.example.blackblog.enums.ReactionsType;

import javax.persistence.*;

@Entity
@Table(name = "reactions")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReactionsType reactionType;

    @Enumerated(EnumType.STRING)
    private ReactionEnable isEnable;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public Reaction() {
    }

    public Reaction(ReactionsType reactionType, User user, Comment comment) {
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

    public ReactionsType getReactionType() {
        return reactionType;
    }

    public void setReactionType(ReactionsType reactionType) {
        this.reactionType = reactionType;
    }

    public ReactionEnable getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(ReactionEnable isEnable) {
        this.isEnable = isEnable;
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
