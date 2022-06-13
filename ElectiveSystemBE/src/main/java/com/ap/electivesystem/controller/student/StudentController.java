package com.ap.electivesystem.controller.student;

import com.ap.electivesystem.config.auth.annotation.Student;
import com.ap.electivesystem.entity.constant.ReturnCode;
import com.ap.electivesystem.entity.vo.CourseVO;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.entity.vo.ScoreVO;
import com.ap.electivesystem.service.CourseService;
import com.ap.electivesystem.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//@Student
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @Resource
    private CourseService courseService;


    @GetMapping("/")
    public String hello() {
        return "This is student!";
    }

    @GetMapping("/schedule")
    public ResultVO schedule() {
        List<CourseVO> schedule = studentService.schedule();
        if (schedule == null)
            return ResultVO.fail(ReturnCode.COURSE_NOT_FOUND);
        return ResultVO.success(schedule);
    }

    @GetMapping("/score")
    public ResultVO score(){
        List<ScoreVO> score = studentService.score();
        if (score == null)
            return ResultVO.fail(ReturnCode.COURSE_NOT_FOUND);
        return ResultVO.success(score);
    }

    @PutMapping("/select/{courseId}")
    public ResultVO select(@PathVariable Integer courseId){
        return ResultVO.success(studentService.insertSelect(courseId));
    }

    @PostMapping("/select")
    public ResultVO select(@RequestBody List<Integer> courseIds){
        return ResultVO.success(studentService.insertSelectBatch(courseIds));
    }

    @DeleteMapping("/deselect/{courseId}")
    public ResultVO deselect(@PathVariable Integer courseId){
        return ResultVO.success(studentService.deleteSelect(courseId));
    }

    @GetMapping("/course/list")
    public ResultVO list(){
        return ResultVO.success(courseService.list());
    }

}
