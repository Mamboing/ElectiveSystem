package com.ap.electivesystem.controller.admin;

import com.ap.electivesystem.config.auth.annotation.Register;
import com.ap.electivesystem.entity.Student;
import com.ap.electivesystem.entity.constant.ReturnCode;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.service.StudentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Register(Register.STUDENT_MANAGE)
@RestController
@RequestMapping("/admin/student/")
public class AdminStudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("/list")
    public ResultVO list(@RequestParam(required = false) String studentName, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "1") int pageNo){
        if(pageNo < 0 || pageSize < 0)
            return ResultVO.fail(ReturnCode.PAGE_PARAMETER_ERROR);
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        if(studentName != null && !studentName.equals(""))
            wrapper.eq(Student::getStudentName, studentName);
        PageHelper.startPage(pageNo,pageSize);
        List<Student> list = studentService.list(wrapper);
        return ResultVO.success(new PageInfo<>(list));
    }

    @PostMapping("/update")
    public ResultVO update(@RequestBody Student student){
        if(student.getStudentId() == null)
            return ResultVO.fail(ReturnCode.ID_NULL_ERROR);
        return ResultVO.success(studentService.updateById(student));
    }

    @PostMapping("/update/batch")
    public ResultVO updateBatch(@RequestBody List<Student> students){
        return ResultVO.success(studentService.updateBatchById(students));
    }

    @PostMapping("/add")
    public ResultVO add(@RequestBody Student student){
        return ResultVO.success(studentService.save(student));
    }

    @PostMapping("/add/batch")
    public ResultVO addBatch(@RequestBody List<Student> students){
        return ResultVO.success(studentService.saveBatch(students));
    }

    @DeleteMapping("/delete/{id}")
    public ResultVO delete(@PathVariable Integer id){
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getStudentId, id);
        return ResultVO.success(studentService.remove(wrapper));
    }

    @PostMapping("/delete/batch")
    public ResultVO delete(@RequestBody List<Integer> id){
        return ResultVO.success(studentService.removeBatchByIds(id));
    }

}
