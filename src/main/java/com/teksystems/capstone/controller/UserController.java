package com.teksystems.capstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("/user/sign-up")
    public ModelAndView displaySignUpPage() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/sign-up");

        return response;
    }
}
