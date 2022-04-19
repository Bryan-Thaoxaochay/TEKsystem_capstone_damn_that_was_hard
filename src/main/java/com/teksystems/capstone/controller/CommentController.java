package com.teksystems.capstone.controller;

import com.teksystems.capstone.bean.CommentBean;
import com.teksystems.capstone.database.dao.CommentDAO;
import com.teksystems.capstone.database.dao.UserDAO;
import com.teksystems.capstone.database.entity.Comment;
import com.teksystems.capstone.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class CommentController {
    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private UserDAO userDAO;

    // Creating a comment
    @PostMapping("/comment/create/{postId}")
    public ModelAndView createComment(@PathVariable("postId") Integer postId, CommentBean form) {
        ModelAndView response = new ModelAndView();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        User user = userDAO.findByEmail(userEmail);

        Comment comment = new Comment();
        comment.setComment(form.getComment());
        comment.setBlogpostId(postId);
        comment.setUserId(user.getUserId());

        commentDAO.save(comment);

        response.setViewName("redirect:/posts/post/" + postId);
        return response;
    }

    // Editing a comment
    @PostMapping("/comment/edit/{commentId}/{postId}/{userId}")
    public ModelAndView editComment(@PathVariable("commentId") Integer commentId, @PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId, CommentBean form) {
        ModelAndView response = new ModelAndView();

        Comment comment = commentDAO.findById(commentId);
        comment.setId(commentId);
        comment.setComment(form.getComment());
        comment.setBlogpostId(postId);
        comment.setUserId(userId);
        comment.setUpdateDate(new Date());

        commentDAO.save(comment);

        response.setViewName("redirect:/posts/post/" + postId);
        return response;
    }

    // Deleting a comment
    @PostMapping("/comment/delete/{commentId}")
    public ModelAndView deleteComment(@PathVariable("commentId") Integer commentId, @RequestParam("id") Integer postId) {
        ModelAndView response = new ModelAndView();

        Comment comment = commentDAO.findById(commentId);
        commentDAO.delete(comment);

        response.setViewName("redirect:/posts/post/" + postId);
        return response;
    }
}
