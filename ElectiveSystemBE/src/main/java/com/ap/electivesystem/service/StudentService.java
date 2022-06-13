package com.ap.electivesystem.service;

import com.ap.electivesystem.entity.Student;
import com.ap.electivesystem.entity.vo.CourseVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface StudentService extends IService<Student> {
    List<CourseVO> schedule();
}
