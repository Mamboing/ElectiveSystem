package com.ap.electivesystem.controller.admin;

import com.ap.electivesystem.config.auth.annotation.Register;
import com.ap.electivesystem.entity.Course;
import com.ap.electivesystem.entity.Teacher;
import com.ap.electivesystem.entity.constant.ReturnCode;
import com.ap.electivesystem.entity.dto.CourseDTO;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.service.CourseService;
import com.ap.electivesystem.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 需要实现的功能：老师开设课程的审核、开始增设课程、结束增设课程
 */
@Register(Register.COURSE_MANAGE)
@RestController
@RequestMapping("/admin/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @GetMapping("/list")
    public ResultVO list(@RequestParam(required = false) String courseName, @RequestParam(required = false) String weekday, @RequestParam(required = false) String time, @RequestParam(required = false) String teacherName, @RequestParam(required = false) String courseRoom, @RequestParam(required = false) String offerState, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "1") int pageNo){
        if(pageNo < 0 || pageSize < 0)
            return ResultVO.fail(ReturnCode.PAGE_PARAMETER_ERROR);
        PageInfo<CourseDTO> search = courseService.search(courseName, weekday, time, teacherName, courseRoom, offerState, pageSize, pageNo);
        return ResultVO.success(search);
    }

    @PostMapping("/update")
    public ResultVO update(@RequestBody Course course){
        if(course.getCourseId() == null)
            return ResultVO.fail(ReturnCode.ID_NULL_ERROR);
        return ResultVO.success(courseService.updateById(course));
    }

    @PostMapping("/update/batch")
    public ResultVO updateBatch(@RequestBody List<Course> courses){
        return ResultVO.success(courseService.updateBatchById(courses));
    }

    @DeleteMapping("/delete/{id}")
    public ResultVO delete(@PathVariable Integer id){
        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Course::getCourseId, id);
        return ResultVO.success(courseService.remove(wrapper));
    }

    @PostMapping("/delete/batch")
    public ResultVO delete(@RequestBody List<Integer> id){
        return ResultVO.success(courseService.removeBatchByIds(id));
    }

    @PutMapping("/add/start")
    public ResultVO start(){
        return null;
    }

    @PutMapping("/add/finish")
    public ResultVO finish(){
        return null;
    }

}
