package com.ap.electivesystem.controller.student;

import com.ap.electivesystem.config.auth.annotation.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Student
@RestController
@RequestMapping("/student")
public class StudentTestController {

    @GetMapping("/")
    public String hello(){
        return "This is student!";
    }

}
