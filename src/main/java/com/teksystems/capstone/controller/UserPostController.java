package com.teksystems.capstone.controller;

import com.teksystems.capstone.database.dao.PostDAO;
import com.teksystems.capstone.database.dao.UserDAO;
import com.teksystems.capstone.database.dao.UserPostDAO;
import com.teksystems.capstone.database.entity.Post;
import com.teksystems.capstone.database.entity.User;
import com.teksystems.capstone.database.entity.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAuthority('USER')")
public class UserPostController {
    @Autowired
    private UserPostDAO userPostDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PostDAO postDAO;

    // Display saved posts
    @GetMapping("/posts/saved_posts")
    public ModelAndView getSavedPosts(@RequestParam(value = "notSaved", defaultValue = "false") Boolean postSaved) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userDAO.findByEmail(authentication.getName());

        List<UserPost> userPostList = userPostDAO.getAllByUserId(currentUser.getUserId());
        List<Post> savedPosts = new ArrayList<>();
        userPostList.forEach(userPost -> savedPosts.add(postDAO.findById(userPost.getPostId())));

        ModelAndView response = new ModelAndView("post/saved_posts").addObject("posts", savedPosts);
        if (postSaved) { response.addObject("unableToSave", "Story already saved"); }

        return response;
    }

    // Saving a post
    @PostMapping("/posts/save")
    public ModelAndView savePost(@RequestParam("id") Integer postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userDAO.findByEmail(authentication.getName());

        if (userPostDAO.findUserPostByUserIdAndPostId(currentUser.getUserId(), postId) != null) {
            return new ModelAndView("redirect:/posts/saved_posts").addObject("notSaved", "true");
        }

        UserPost userPost = new UserPost();
            userPost.setUserId(currentUser.getUserId());
            userPost.setPostId(postId);
            userPostDAO.save(userPost);

        return new ModelAndView("redirect:/posts/saved_posts");
    }

    // Removing a post
    @PostMapping("/posts/saved_posts/{id}")
    public ModelAndView removePost(@RequestParam("postId") Integer postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userDAO.findByEmail(authentication.getName());

        UserPost userPost = userPostDAO.findUserPostByUserIdAndPostId(currentUser.getUserId(), postId);
        userPostDAO.delete(userPost);

        return new ModelAndView("redirect:/posts/saved_posts");
    }
}
