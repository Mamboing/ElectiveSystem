package com.ap.electivesystem.controller.teacher;

import com.ap.electivesystem.config.auth.annotation.Teacher;
import com.ap.electivesystem.entity.Score;
import com.ap.electivesystem.entity.constant.ReturnCode;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.entity.vo.StudentVO;
import com.ap.electivesystem.service.CourseService;
import com.ap.electivesystem.service.ScoreService;
import com.ap.electivesystem.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Teacher
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private CourseService courseService;

    @Resource
    private ScoreService scoreService;

    @Resource
    private StudentService studentService;

    @GetMapping("/")
    public String hello() {
        return "This is Teacher!";
    }

    @PutMapping("/add/course")
    public ResultVO addCourse(@RequestParam String courseName, @RequestParam String weekday, @RequestParam String time){
        return ResultVO.success(courseService.saveNoId(courseName, weekday, time));
    }

    @PostMapping("/add/score")
    public ResultVO addScore(@RequestBody List<Score> list){
        return ResultVO.success(scoreService.addScore(list));
    }

    @GetMapping("/find/{courseId}")
    public ResultVO findByCourseId(@PathVariable Integer courseId){
        List<StudentVO> studentVOS = studentService.findByCourseId(courseId);
        if(studentVOS.isEmpty())
            return ResultVO.fail(ReturnCode.STUDENT_NOT_FOUND);
        return ResultVO.success(studentVOS);
    }

}
