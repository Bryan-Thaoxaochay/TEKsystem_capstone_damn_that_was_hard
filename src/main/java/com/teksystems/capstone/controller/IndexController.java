package com.teksystems.capstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/home/index")
    public ModelAndView index() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("home/index");

        return response;
    }
}
