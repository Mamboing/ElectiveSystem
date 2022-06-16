package com.ap.electivesystem.service;

import com.ap.electivesystem.entity.Teacher;
import com.ap.electivesystem.entity.vo.CourseVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TeacherService extends IService<Teacher> {

    Teacher getByName(String userName);

    List<CourseVO> schedule();

}
