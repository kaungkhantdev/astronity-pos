package org.astronity.pos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @GetMapping()
    public String index()
    {
        return "pages/users/list";
    }

    @GetMapping("/{id}")
    public String show()
    {
        return "pages/users/show";
    }

    @GetMapping("/new")
    public String create()
    {
        return "pages/users/create";
    }

    @GetMapping("/{id}/edit")
    public String edit()
    {
        return "pages/users/edit";
    }
}
