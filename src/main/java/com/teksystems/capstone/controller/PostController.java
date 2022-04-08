package com.teksystems.capstone.controller;

import com.teksystems.capstone.bean.PostBean;
import com.teksystems.capstone.database.dao.PostDAO;
import com.teksystems.capstone.database.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostDAO postDAO;

    @GetMapping("/posts")
    public ModelAndView displayPostsPage() {
        ModelAndView response = new ModelAndView();
        response.setViewName("post/posts");

        List<Post> posts = postDAO.findAll();
        response.addObject("posts", posts);

        return response;
    }

    @GetMapping("/posts/create_post")
    public ModelAndView displayCreatePostPage() {
        ModelAndView response = new ModelAndView();
        response.setViewName("post/create_post");

        return response;
    }

    @PostMapping("/posts/new_post")
    public ModelAndView createPost(PostBean form) {
        ModelAndView response = new ModelAndView();

        Post post = new Post();
        post.setTopic(form.getTopic());
        post.setTitle(form.getTitle());
        post.setDescription(form.getDescription());

        postDAO.save(post);

        response.setViewName("post/posts");
        return response;
    }
}
