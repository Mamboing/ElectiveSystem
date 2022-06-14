package com.ap.electivesystem.controller.teacher;

import com.ap.electivesystem.config.auth.annotation.Teacher;
import com.ap.electivesystem.entity.Score;
import com.ap.electivesystem.entity.constant.ReturnCode;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.entity.vo.StudentVO;
import com.ap.electivesystem.service.CourseService;
import com.ap.electivesystem.service.ScoreService;
import com.ap.electivesystem.service.StudentService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    public ResultVO addCourse(@RequestParam String courseName, @RequestParam String weekday, @RequestParam String time) {
        return ResultVO.success(courseService.saveNoId(courseName, weekday, time));
    }

    @PostMapping("/add/score")
    public ResultVO addScore(@RequestBody List<Score> list) {
        return ResultVO.success(scoreService.addScore(list));
    }

    @GetMapping("/find/{courseId}")
    @ApiOperation("返回 vo.StudentVO 的列表，包装为PageInfo(其中含有属性total、list(就是 vo.StudentVO 的列表)以及传入的分页参数)")
    @ApiResponses({
            @ApiResponse(code = 1003, message = "没有找到对应的学生"),
            @ApiResponse(code = 0, message = "success")
    })
    public ResultVO findByCourseId(@PathVariable Integer courseId, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "1") int pageNo) {
        PageInfo<StudentVO> studentVOS = studentService.findByCourseId(courseId, pageSize, pageNo);
        if (studentVOS.getSize() == 0)
            return ResultVO.fail(ReturnCode.STUDENT_NOT_FOUND);
        return ResultVO.success(studentVOS);
    }

}
