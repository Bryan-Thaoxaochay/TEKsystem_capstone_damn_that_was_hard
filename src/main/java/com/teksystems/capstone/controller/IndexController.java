package com.teksystems.capstone.controller;

import com.teksystems.capstone.database.dao.UserDAO;
import com.teksystems.capstone.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/home/index")
    public ModelAndView index() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) { return new ModelAndView("home/index"); }

        String userEmail = authentication.getName();
        User user = userDAO.findByEmail(userEmail);
        return new ModelAndView("home/index").addObject("user", user);
    }
}
