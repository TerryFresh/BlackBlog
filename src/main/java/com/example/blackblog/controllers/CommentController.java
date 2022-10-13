package com.example.blackblog.controllers;

import com.example.blackblog.entity.Comment;
import com.example.blackblog.entity.User;
import com.example.blackblog.repo.CommentRepo;
import com.example.blackblog.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class CommentController {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;

//    @GetMapping("/")
//    public String home(Model model) {
//        Iterable<Comment> comments = commentRepo.findAll();
//        model.addAttribute("comments", comments);
//
//        return "home-view";
//    }

    @GetMapping("/")
    public String home(Model model) {
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);

        return "home-view";
    }

    @PostMapping
    public String addComment(@RequestParam String text, Model model) {
        Comment comment = new Comment(text);
        commentRepo.save(comment);
        Iterable<Comment> comments = commentRepo.findAll();
        model.addAttribute("comments", comments);

        return "home-view";
    }

//    filter by comment text (не ищет по юзеру)
//    @PostMapping("/filter")
//    public String filter(@RequestParam String filter, Model model) {
//        Iterable<Comment> comments;
//        if (filter != null && !filter.isEmpty()){
//            comments = commentRepo.findByText(filter);
//        } else {
//            comments = commentRepo.findAll();
//        }
//        model.addAttribute("comments", comments);
//
//        return "home-view";
//    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model) {
        Optional<User> users;
        if (filter != null && !filter.isEmpty()){
            users = userRepo.findByUsername(filter);
            ArrayList<User> res = new ArrayList<>();
            users.ifPresent(res::add);
            model.addAttribute("users", res);
        }
        else {
           return "redirect:/";
        }

        return "home-view";
    }
}