package com.teksystems.capstone.controller;

import com.teksystems.capstone.database.dao.UserDAO;
import com.teksystems.capstone.database.dao.UserPostDAO;
import com.teksystems.capstone.database.entity.User;
import com.teksystems.capstone.database.entity.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserPostController {
    @Autowired
    private UserPostDAO userPostDAO;

    @Autowired
    private UserDAO userDAO;

    // Display saved posts

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

        response.setViewName("redirect:/home/index");
        return response;
    }
}
