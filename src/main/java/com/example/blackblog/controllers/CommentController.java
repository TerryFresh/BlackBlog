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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;


@Controller
public class CommentController {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ReactionRepo reactionRepo;

    @Autowired
    SessionFactory sessionFactory;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Comment> comments = commentRepo.findAll();
        model.addAttribute("comments", comments);

        Iterable<Reaction> reactions = reactionRepo.findAll();
        model.addAttribute("reactions", reactions);

        return "home-view";
    }

    @PostMapping("/add")
    public String addComment(@RequestParam String text, @RequestParam String replyId, @RequestParam String userId, Model model) {
        Comment reply = null;
        if (!userRepo.existsById(Long.parseLong(userId))) {
            return "redirect:/";
        }

        if (replyId != null && !replyId.isEmpty()) {
            if (!commentRepo.existsById(Long.parseLong(replyId))) {
                return "redirect:/";
            }
        }
        User user = userRepo.findById(Long.parseLong(userId)).orElse(null); // добавил .orElse(null); тк findById возвращает Optional<T> который не подходит для дальнейшей передачи
        ArrayList<User> res = new ArrayList<>();
        model.addAttribute("users", res);

        if (replyId != null && !replyId.isEmpty()) {
            reply = commentRepo.findById(Long.parseLong(replyId)).orElse(null);
            ArrayList<User> res2 = new ArrayList<>();
            model.addAttribute("replies", res2);
        }

        Comment comment = new Comment(text, user, reply);
        commentRepo.save(comment);
        Iterable<Comment> comments = commentRepo.findAll();
        model.addAttribute("comments", comments);

        return "home-view";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model) {

        if (filter != null && !filter.isEmpty()) {
            Iterable<Comment> comments = commentRepo.findByUserId(Long.parseLong(filter));
            model.addAttribute("comments", comments);

        } else {
            return "redirect:/";
        }

        return "home-view";
    }

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