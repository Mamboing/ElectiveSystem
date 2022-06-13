package com.ap.electivesystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {
    @RequestMapping("/api")
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
