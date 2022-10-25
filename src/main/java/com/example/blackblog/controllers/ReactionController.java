package com.example.blackblog.controllers;

import com.example.blackblog.entity.Comment;
import com.example.blackblog.entity.Reaction;
import com.example.blackblog.entity.User;
import com.example.blackblog.enums.ReactionEnable;
import com.example.blackblog.enums.ReactionsType;
import com.example.blackblog.repo.CommentRepo;
import com.example.blackblog.repo.ReactionRepo;
import com.example.blackblog.repo.UserRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Transactional
public class ReactionController {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ReactionRepo reactionRepo;

    @Autowired
    SessionFactory sessionFactory;

    @PostMapping("/reactions")
    public String reactions(@RequestParam String reactionType, @RequestParam String userId, @RequestParam String commentId, Model model) {
        if (!userRepo.existsById(Long.parseLong(userId))) {
            return "redirect:/";
        }
        if (commentRepo != null && !commentId.isEmpty()) {
            if (!commentRepo.existsById(Long.parseLong(commentId))) {
                return "redirect:/";
            }
        }

        User user = userRepo.findById(Long.parseLong(userId)).orElse(null);
        Comment comment = commentRepo.findById(Long.parseLong(commentId)).orElse(null);

        if (!reactionRepo.findByUserAndCommentAndIsEnable(user, comment, ReactionEnable.valueOf("ENABLE")).isEmpty()) {
            Session session = sessionFactory.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            try {
                Query query = session.createQuery("UPDATE Reaction SET isEnable = :isEnable WHERE user = :userId AND comment = :commentId");
                query
                        .setParameter("isEnable", ReactionEnable.DISABLE)
                        .setParameter("userId", user)
                        .setParameter("commentId", comment);
                int result = query.executeUpdate();
                tx.commit();
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
            session.close();

            if (!reactionRepo.findByUserAndCommentAndReactionType(user, comment, ReactionsType.valueOf(reactionType.toUpperCase())).isEmpty()) {
                return "redirect:/";
            }
        }
        Reaction reaction = new Reaction(ReactionsType.valueOf(String.valueOf(reactionType).toUpperCase()), ReactionEnable.valueOf("ENABLE"), user, comment);
        reactionRepo.save(reaction);
        Iterable<Reaction> reactions = reactionRepo.findAll();
        model.addAttribute("reactions", reactions);

        return "home-view";
    }
}
