package com.teksystems.capstone.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.teksystems.capstone.bean.SignUpBean;
import com.teksystems.capstone.database.dao.UserDAO;
import com.teksystems.capstone.database.dao.UserRoleDAO;
import com.teksystems.capstone.database.entity.User;
import com.teksystems.capstone.database.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/user/sign_up")
    public ModelAndView displaySignUpPage() { return new ModelAndView("user/sign_up"); }

    @GetMapping("/user/sign_in")
    public ModelAndView displaySignInPage(@RequestParam(value = "error", defaultValue = "false") Boolean signInError) {
        if (signInError) {
            return new ModelAndView("user/sign_in").addObject("signInErrorMsg", "Username or password is incorrect.");
        }

        return new ModelAndView("user/sign_in");
    }

    @PostMapping("/user/create")
    public ModelAndView createUser(@Valid SignUpBean form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("user/sign_up")
                    .addObject("bindingResult", bindingResult).addObject("form", form);
        }

        if (!form.getPassword().equals(form.getConfirmPassword())) {
            return new ModelAndView("user/sign_up").addObject("form", form)
                    .addObject("passwordMessage", "This doesn't match the password above.");
        }

        User user = new User();
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setUsername(form.getUsername());
            user.setEmail(form.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(form.getPassword()));
            userDAO.save(user);

        UserRole userRole = new UserRole();
            User createdUser = userDAO.findByUsername(form.getUsername());
            userRole.setUserId(createdUser.getUserId());
            userRoleDAO.save(userRole);

        return new ModelAndView("redirect:/home/index");
    }

    @GetMapping("/user/information")
    public ModelAndView displayUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userDAO.findByEmail(authentication.getName());

        return new ModelAndView("user/user_profile").addObject("currentUser", currentUser);
    }
}
