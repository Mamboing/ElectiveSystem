package com.ap.electivesystem.controller.admin;

import com.ap.electivesystem.config.auth.annotation.Register;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Register(Register.COURSE_MANAGE)
@RestController
@RequestMapping("/admin/course")
public class CourseController {
}
