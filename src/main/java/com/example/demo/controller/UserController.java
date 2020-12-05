package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/registration")
    public ModelAndView getRegPage(ModelAndView modelAndView) {
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(path = "/registration")
    public ModelAndView getRegPage(@Valid User user, ModelAndView modelAndView) {
        userService.save(user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @GetMapping(path = "/authorization")
    public ModelAndView getAuthPage(ModelAndView modelAndView) {
        modelAndView.setViewName("authorization");
        return modelAndView;
    }

    @PostMapping(path = "/authorization")
    public ModelAndView getAuthPage(User user, ModelAndView modelAndView, HttpSession httpSession) {
        User byLogin = userService.findByLogin(user.getLogin());
        if (byLogin != null) {
            if (byLogin.getPassword().equals(user.getPassword())) {
                httpSession.setAttribute("user", byLogin);
                modelAndView.setViewName("redirect:/");
            } else {
                modelAndView.addObject("message", "Wrong password!");
                modelAndView.setViewName("authorization");
            }
        } else {
            modelAndView.addObject("message", "User not found!");
            modelAndView.setViewName("authorization");
        }
        return modelAndView;
    }

    @GetMapping(path = "/logout")
    public ModelAndView getAuthPage(HttpSession httpSession, ModelAndView modelAndView) {
        httpSession.invalidate();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
