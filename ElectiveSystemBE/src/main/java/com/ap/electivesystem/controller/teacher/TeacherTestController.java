package com.ap.electivesystem.controller.teacher;

import com.ap.electivesystem.config.auth.annotation.Teacher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Teacher
@RestController
@RequestMapping("/teacher")
public class TeacherTestController {
    @GetMapping("/")
    public String hello(){
        return "This is Teacher!";
    }
}
