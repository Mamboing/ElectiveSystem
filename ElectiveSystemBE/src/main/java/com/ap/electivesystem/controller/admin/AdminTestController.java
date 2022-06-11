package com.ap.electivesystem.controller.admin;

import com.ap.electivesystem.config.auth.annotation.Register;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Register
@RestController
@RequestMapping("/admin")
public class AdminTestController {

    @GetMapping("/")
    public String hello(){
        return "This is register!";
    }

}
