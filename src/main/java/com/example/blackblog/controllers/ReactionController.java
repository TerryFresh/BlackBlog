package com.example.blackblog.controllers;

import com.example.blackblog.entity.Comment;
import com.example.blackblog.entity.Reaction;
import com.example.blackblog.entity.User;
import com.example.blackblog.enums.ReactionsType;
import com.example.blackblog.repo.CommentRepo;
import com.example.blackblog.repo.ReactionRepo;
import com.example.blackblog.repo.UserRepo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
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
        Reaction reaction = new Reaction(ReactionsType.valueOf(String.valueOf(reactionType).toUpperCase()), user, comment);

        if (!reactionRepo.findByUserId(Long.parseLong(userId)).isEmpty()) {
            if (!reactionRepo.findByCommentId(Long.parseLong(commentId)).isEmpty()) {
//                if (!reactionRepo.)

                Session session;

                try {
                    session = sessionFactory.getCurrentSession();
                } catch (HibernateException e) {
                    session = sessionFactory.openSession();
                }

                try {
                    String hql = "UPDATE Reaction SET reactionType = :reactionType WHERE user= :user AND comment= :comment";
                    Query query = session.createQuery(hql);
                    query
//                            .setParameter("userId", userRepo.findById(Long.parseLong(userId)))
//                            .setParameter("commentId", commentRepo.findById(Long.parseLong(commentId)))
                            .setParameter("reactionType", reaction.getReactionType());
                    session.saveOrUpdate(reaction);

                } catch (Exception e) {
                    System.out.println("nihuya");
                }
                if (session.getTransaction().isActive()) {
                    session.getTransaction().commit();
                }
                session.close();
            }
            return "redirect:/";
        }

        reactionRepo.save(reaction);
        Iterable<Reaction> reactions = reactionRepo.findAll();
        model.addAttribute("reactions", reactions);

        return "home-view";
    }
}
