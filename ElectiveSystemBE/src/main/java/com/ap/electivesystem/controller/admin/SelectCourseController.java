package com.ap.electivesystem.controller.admin;


import com.ap.electivesystem.config.auth.annotation.Register;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 需要完成的功能：开始选课、结束选课、选课审核
 */
@Register(Register.SELECT_COURSE_MANAGE)
@RequestMapping("/admin/select/course/")
@RestController
public class SelectCourseController {
}
