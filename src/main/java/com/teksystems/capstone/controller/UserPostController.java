package com.teksystems.capstone.controller;

import com.teksystems.capstone.database.dao.PostDAO;
import com.teksystems.capstone.database.dao.UserDAO;
import com.teksystems.capstone.database.dao.UserPostDAO;
import com.teksystems.capstone.database.entity.Post;
import com.teksystems.capstone.database.entity.User;
import com.teksystems.capstone.database.entity.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserPostController {
    @Autowired
    private UserPostDAO userPostDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PostDAO postDAO;

    // Display saved posts
    @GetMapping("/posts/saved_posts")
    public ModelAndView getSavedPosts() {
        ModelAndView response = new ModelAndView();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userDAO.findByEmail(userEmail);

        List<UserPost> userPostList = userPostDAO.getAllByUserId(user.getUserId());

        List<Post> savedPosts = new ArrayList<>();

        for (UserPost userPost : userPostList) {
            Post savedPost = postDAO.findById(userPost.getPostId());
            savedPosts.add(savedPost);
        }

        response.addObject("posts", savedPosts);
        response.setViewName("post/posts");
        return response;
    }

    // Saving a post
    @PostMapping("/posts/save")
    public ModelAndView savePost(@RequestParam("id") Integer postId) {
        ModelAndView response = new ModelAndView();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userDAO.findByEmail(userEmail);

        UserPost userPost = new UserPost();
        userPost.setUserId(user.getUserId());
        userPost.setPostId(postId);

        userPostDAO.save(userPost);

        response.setViewName("redirect:/posts/saved_posts");
        return response;
    }
}
