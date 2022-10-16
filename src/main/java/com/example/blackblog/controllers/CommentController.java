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
import java.util.Optional;


@Controller
public class CommentController {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Comment> comments = commentRepo.findAll();
        model.addAttribute("comments", comments);

        return "home-view";
    }


    @PostMapping("/add")
    public String addComment(@RequestParam String text, @RequestParam String replyId, @RequestParam String userId, Model model) {
        if (!userRepo.existsById(Long.parseLong(userId))) {
            return "redirect:/";
        }
        if (!commentRepo.existsById(Long.parseLong(replyId))) {
            return "redirect:/";
        }

        User user = userRepo.findById(Long.parseLong(userId)).orElse(null); // добавил .orElse(null); тк findById возвращает Optional<T> который не подходит для дальнейшей передачи
        ArrayList<User> res = new ArrayList<>();
        model.addAttribute("users", res);

        Comment reply = commentRepo.findById(Long.parseLong(replyId)).orElse(null);
        ArrayList<User> res2 = new ArrayList<>();
        model.addAttribute("replies", res2);

        Comment comment = new Comment(text, user, reply);
        commentRepo.save(comment);
        Iterable<Comment> comments = commentRepo.findAll();
        model.addAttribute("comments", comments);

        return "home-view";
    }


//    @PostMapping("/filter")
//    public String filter(@RequestParam String filter, Model model) {
//        Optional<User> users;
//        if (filter != null && !filter.isEmpty()){
//            users = userRepo.findByUsername(filter);
//            ArrayList<User> res = new ArrayList<>();
//            users.ifPresent(res::add);
//            model.addAttribute("users", res);
//        }
//        else {
//           return "redirect:/";
//        }
//
//        return "home-view";
//    }

}