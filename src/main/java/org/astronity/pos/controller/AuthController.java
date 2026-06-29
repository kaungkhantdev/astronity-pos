package org.astronity.pos.controller;

import org.astronity.pos.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String login(Model model) {
        return "pages/auth/login";
    }
}
