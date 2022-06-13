package com.ap.electivesystem.controller.admin;


import com.ap.electivesystem.config.auth.annotation.Register;
import com.ap.electivesystem.entity.Teacher;
import com.ap.electivesystem.entity.constant.ReturnCode;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Register(Register.TEACHER_MANAGE)
@RequestMapping("/admin/teacher")
@RestController
public class AdminTeacherController {

    @Resource
    private TeacherService teacherService;

    @GetMapping("/list")
    public ResultVO list(@RequestParam(required = false) String teacherName, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "1") int pageNo){
        if(pageNo < 0 || pageSize < 0)
            return ResultVO.fail(ReturnCode.PAGE_PARAMETER_ERROR);
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        if(teacherName != null && !teacherName.equals(""))
            wrapper.eq(Teacher::getTeacherName, teacherName);
        PageHelper.startPage(pageNo,pageSize);
        List<Teacher> list = teacherService.list(wrapper);
        return ResultVO.success(new PageInfo<>(list));
    }

    @PostMapping("/update")
    public ResultVO update(@RequestBody Teacher teacher){
        if(teacher.getTeacherId() == null)
            return ResultVO.fail(ReturnCode.ID_NULL_ERROR);
        return ResultVO.success(teacherService.updateById(teacher));
    }

    @PostMapping("/update/batch")
    public ResultVO updateBatch(@RequestBody List<Teacher> teachers){
        return ResultVO.success(teacherService.updateBatchById(teachers));
    }

    @PostMapping("/add")
    public ResultVO add(@RequestBody Teacher teacher){
        return ResultVO.success(teacherService.save(teacher));
    }

    @PostMapping("/add/batch")
    public ResultVO addBatch(@RequestBody List<Teacher> teachers){
        return ResultVO.success(teacherService.saveBatch(teachers));
    }

    @DeleteMapping("/delete/{id}")
    public ResultVO delete(@PathVariable Integer id){
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teacher::getTeacherId, id);
        return ResultVO.success(teacherService.remove(wrapper));
    }

    @PostMapping("/delete/batch")
    public ResultVO delete(@RequestBody List<Integer> id){
        return ResultVO.success(teacherService.removeBatchByIds(id));
    }

}
