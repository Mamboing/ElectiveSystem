package com.ap.electivesystem.service;

import com.ap.electivesystem.entity.Course;
import com.ap.electivesystem.entity.dto.CourseDTO;
import com.ap.electivesystem.entity.vo.CourseVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

public interface CourseService extends IService<Course> {

    int saveNoId(String courseName, String weekday, String time);

    PageInfo<CourseVO> find(String courseName, String courseTime, String teacherName, int pageSize, int pageNo);

    PageInfo<CourseDTO> search(String courseName, String weekday, String time, String teacherName, String courseRoom, String offerState, int pageSize, int pageNo);

}
