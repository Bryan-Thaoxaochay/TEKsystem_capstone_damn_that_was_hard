package com.teksystems.capstone.controller;

import com.teksystems.capstone.bean.SignUpBean;
import com.teksystems.capstone.database.dao.UserDAO;
import com.teksystems.capstone.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/user/sign_up")
    public ModelAndView displaySignUpPage() throws Exception { return new ModelAndView("user/sign_up"); }

    @GetMapping("/user/sign_in")
    public ModelAndView displaySignInPage() throws Exception { return new ModelAndView("user/sign_in"); }

    @PostMapping("/user/create")
    public ModelAndView createUser(@Valid SignUpBean form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("user/sign_up").addObject("bindingResult", bindingResult).addObject("form", form);
        }

        if (form.getPassword() != form.getConfirmPassword()) {
            String passwordMessage = "This doesn't match the password above.";
            return new ModelAndView("user/sign_up").addObject("passwordMessage", passwordMessage);
        }

        User user = new User();
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(form.getPassword()));
        userDAO.save(user);

        return new ModelAndView("redirect:/home/index");
    }
}
