package com.itproger.blog.controllers;

import com.itproger.blog.models.Post;
import com.itproger.blog.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepo postRepo;

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepo.findAll();
        model.addAttribute("posts", posts);

        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd() {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogAddPost(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String author,
                              @RequestParam String period,
                              @RequestParam String full_text
    ){
        Post post = new Post(title, anons, author, period, full_text);
        postRepo.save(post);

        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") Long id,  Model model) {
        if (!postRepo.existsById(id)) {

            return "redirect:/blog";
        }
        Optional<Post> post = postRepo.findById(id);
        AbstractList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);

        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") Long id,  Model model) {
        if (!postRepo.existsById(id)) {

            return "redirect:/blog";
        }
        Optional<Post> post = postRepo.findById(id);
        AbstractList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);

        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id")
                                 long id,
                                 @RequestParam String title,
                                 @RequestParam String anons,
                                 @RequestParam String author,
                                 @RequestParam String period,
                                 @RequestParam String full_text
    ){
        Post post = postRepo.findById(id).orElseThrow(RuntimeException::new);
        post.setTitle(title);
        post.setAnons(anons);
        post.setAuthor(author);
        post.setPeriod(period);
        post.setFull_text(full_text);
        postRepo.save(post);

        return "redirect:/blog/{id}";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") Long id){
        Post post = postRepo.findById(id).orElseThrow(RuntimeException::new);
        postRepo.delete(post);

        return "redirect:/blog/{id}";
    }
}
