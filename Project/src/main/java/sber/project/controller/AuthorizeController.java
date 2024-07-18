package sber.project.controller;

import sber.project.entity.User;
import sber.project.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorizeController {
    private UserService userService;

    @Autowired
    public AuthorizeController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user) {
        userService.createOrUpdateUser(user);
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
