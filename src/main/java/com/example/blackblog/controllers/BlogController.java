package com.example.blackblog.controllers;

import com.example.blackblog.entity.Comment;
import com.example.blackblog.repo.CommentsRepo;
import com.example.blackblog.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BlogController {

    @Autowired
    private CommentsRepo commentsRepo;

    @Autowired
    private UserRepo usersRepo;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Comment> comments = commentsRepo.findAll();
        model.addAttribute("comments", comments);

        return "home-view";
    }

//    @PostMapping
//    public String add(@RequestParam String text, Model model) {
//        Comment comment = new Comment(text, 0, 0);
//        commentsRepo.save(comment);
//        Iterable<Comment> comments = commentsRepo.findAll();
//        model.addAttribute("comments", comments);
//
//        return "home-view";
//    }

    //filter by comment text
    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model) {

        Iterable<Comment> comments;
        if (filter != null && !filter.isEmpty()){
            comments = commentsRepo.findByComment(filter);
        } else {
            comments = commentsRepo.findAll();
        }
        model.addAttribute("comments", comments);

        return "home-view";
    }
}