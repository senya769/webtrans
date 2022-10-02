package com.trans.controllers;

import com.trans.model.User;
import com.trans.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping("/sec")
public class SecurityController {

    private final UserService userService;

    @Autowired
    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String princ(Principal principal) {
        return principal.toString();
    }

    @PostMapping
    public ModelAndView princPost(ModelAndView modelAndView, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        modelAndView.addObject("user_sec",user);
        modelAndView.addObject("principal_test",authentication);
        modelAndView.setViewName("pages/test");
        return modelAndView;
    }
}
