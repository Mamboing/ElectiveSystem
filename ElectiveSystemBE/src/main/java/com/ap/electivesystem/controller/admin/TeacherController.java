package com.ap.electivesystem.controller.admin;


import com.ap.electivesystem.config.auth.annotation.Register;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Register(Register.TEACHER_MANAGE)
@RequestMapping("/admin/teacher")
@RestController
public class TeacherController {
}
