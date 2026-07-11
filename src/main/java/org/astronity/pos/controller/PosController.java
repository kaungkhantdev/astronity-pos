package org.astronity.pos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pos")
public class PosController {
    @GetMapping()
    public String index()
    {
        return "pos/pos/index";
    }
}
