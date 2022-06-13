package com.ap.electivesystem.controller.student;

import com.ap.electivesystem.config.auth.annotation.Student;
import com.ap.electivesystem.entity.vo.CourseVO;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Student
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;


    @GetMapping("/")
    public String hello() {
        return "This is student!";
    }

    @GetMapping("/schedule")
    public ResultVO schedule() {
        List<CourseVO> schedule = studentService.schedule();
//        if (schedule == null) return ResultVO;
        return null;
    }

}
