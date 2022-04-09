package com.teksystems.capstone.controller;

import com.teksystems.capstone.bean.CommentBean;
import com.teksystems.capstone.database.dao.CommentDAO;
import com.teksystems.capstone.database.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {
    @Autowired
    private CommentDAO commentDAO;

    @PostMapping("posts/post/{id}/create_comment")
    public ModelAndView createComment(@PathVariable("id") Integer id, CommentBean form) {
        ModelAndView response = new ModelAndView();

        Comment comment = new Comment();
        comment.setComment(form.getComment());
        comment.setBlogpostId(id);

        commentDAO.save(comment);

        response.setViewName("redirect:/posts/post/" + id);
        return response;
    }
}
