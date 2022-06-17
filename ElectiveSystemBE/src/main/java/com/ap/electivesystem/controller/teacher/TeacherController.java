package com.ap.electivesystem.controller.teacher;

import com.ap.electivesystem.config.auth.annotation.Teacher;
import com.ap.electivesystem.entity.Score;
import com.ap.electivesystem.entity.constant.ReturnCode;
import com.ap.electivesystem.entity.vo.CourseVO;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.entity.vo.StudentScore;
import com.ap.electivesystem.entity.vo.StudentVO;
import com.ap.electivesystem.service.CourseService;
import com.ap.electivesystem.service.ScoreService;
import com.ap.electivesystem.service.StudentService;
import com.ap.electivesystem.service.TeacherService;
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

    @Resource
    private TeacherService teacherService;


    @GetMapping("/")
    public String hello() {
        return "This is Teacher!";
    }

    @PutMapping("/add/course")
    public ResultVO addCourse(@RequestParam String courseName, @RequestParam String weekday, @RequestParam String time, @RequestParam Integer id) {
        int i = courseService.saveNoId(courseName, weekday, time, id);
        switch (i) {
            case -1:
                return ResultVO.fail(ReturnCode.TIME_CONFLICT);
            case -2:
                return ResultVO.fail(ReturnCode.ROOM_FULLY_OCCUPIED);
            default:
                return ResultVO.success(i);
        }
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
    public ResultVO findByCourseId(@PathVariable Integer courseId, @RequestParam Integer teacherId, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(required = false) Integer studentId, @RequestParam(required = false) String studentName) {
        PageInfo<StudentScore> studentVOS = studentService.findByCourseId(courseId, pageSize, pageNo, teacherId, studentId, studentName);
        if (studentVOS.getSize() == 0)
            return ResultVO.fail(ReturnCode.STUDENT_NOT_FOUND);
        return ResultVO.success(studentVOS);
    }

    @GetMapping("/schedule")
    @ApiOperation("返回 vo.CourseVO 的列表")
    @ApiResponses({
            @ApiResponse(code = 1001, message = "没有找到对应的课表"),
            @ApiResponse(code = 0, message = "success")
    })
    public ResultVO schedule(@RequestParam Integer id, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(required = false) Integer studentId, @RequestParam(required = false) String studentName) {
        PageInfo<CourseVO> schedule = teacherService.schedule(id, pageSize,pageNo);
        if (schedule == null)
            return ResultVO.fail(ReturnCode.COURSE_NOT_FOUND);
        return ResultVO.success(schedule);
    }

}
