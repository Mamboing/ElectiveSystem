package com.ap.electivesystem.service;

import com.ap.electivesystem.entity.Course;
import com.ap.electivesystem.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CourseService extends IService<Course> {

    int saveNoId(String courseName, String weekday, String time);

}
