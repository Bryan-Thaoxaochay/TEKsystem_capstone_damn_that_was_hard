package com.teksystems.capstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {
    @GetMapping("/posts")
    public ModelAndView displayPostsPage() {
        ModelAndView response = new ModelAndView();
        response.setViewName("blogposts/posts");

        return response;
    }
}
