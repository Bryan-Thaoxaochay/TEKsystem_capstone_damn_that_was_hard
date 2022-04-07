package com.teksystems.capstone.controller;

import com.teksystems.capstone.bean.SignUpBean;
import com.teksystems.capstone.database.dao.UserDAO;
import com.teksystems.capstone.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/user/sign_up")
    public ModelAndView displaySignUpPage() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/sign_up");

        return response;
    }

    @GetMapping("/user/sign_in")
    public ModelAndView displaySignInPage() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/sign_in");

        return response;
    }

    /*
    As a user, I want to create an account to get account features
    1. Adjust form in sign_up.jsp to send data to this controller
    2. Update User entity to contain getters, setters, and variables in db
    3. Create bean for user registration
    4. Create UserDAO to save user to the db
    5. Create a POST method to handle the form data
    */
    @PostMapping("/user/sign_up/submit")
    public ModelAndView createUser(SignUpBean form) {
        ModelAndView response = new ModelAndView();

        User user = new User();
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());

        userDAO.save(user);

        response.setViewName("home/index");
        return response;
    }
}
