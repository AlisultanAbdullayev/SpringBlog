package com.itproger.blog.controllers;

import com.itproger.blog.models.Post;
import com.itproger.blog.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    PostRepo postRepo;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Post> posts = postRepo.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("title", "Home");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About us");
        return "about";
    }

}