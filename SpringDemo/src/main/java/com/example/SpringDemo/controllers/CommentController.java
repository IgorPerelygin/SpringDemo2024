package com.example.SpringDemo.controllers;

import com.example.SpringDemo.model.Comment;
import com.example.SpringDemo.model.User;
import com.example.SpringDemo.repo.CommentRepo;
import com.example.SpringDemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CommentController {
    @Autowired
    CommentRepo commentRepo;
    @Autowired
    UserRepo userRepo;

    @PostMapping("/addComment")
    public String addComment(@RequestParam String article_id, @RequestParam String comment){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepo.findByEmail(authentication.getName());
        Long userId = user.get().id;
        Comment commentModel = new Comment(comment, userId, Long.parseLong(article_id));
        commentRepo.save(commentModel);
        return "redirect:/blog/"+article_id;
    }
}
