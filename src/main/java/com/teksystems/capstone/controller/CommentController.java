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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userDAO.findByEmail(authentication.getName());

        Comment comment = new Comment();
            comment.setComment(form.getComment());
            comment.setBlogpostId(postId);
            comment.setUserId(currentUser.getUserId());
            commentDAO.save(comment);

        return new ModelAndView("redirect:/posts/post/" + postId);
    }

    // Editing a comment
    @PostMapping("/comment/edit/{commentId}/{postId}/{userId}")
    public ModelAndView editComment(@PathVariable("commentId") Integer commentId, @PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId, CommentBean form) {
        Comment comment = commentDAO.findById(commentId);
            comment.setId(commentId);
            comment.setComment(form.getComment());
            comment.setBlogpostId(postId);
            comment.setUserId(userId);
            comment.setUpdateDate(new Date());
            commentDAO.save(comment);

        return new ModelAndView("redirect:/posts/post/" + postId);
    }

    // Deleting a comment
    @PostMapping("/comment/delete/{commentId}")
    public ModelAndView deleteComment(@PathVariable("commentId") Integer commentId, @RequestParam("id") Integer postId) {
        Comment comment = commentDAO.findById(commentId);
        commentDAO.delete(comment);

        return new ModelAndView("redirect:/posts/post/" + postId);
    }
}
