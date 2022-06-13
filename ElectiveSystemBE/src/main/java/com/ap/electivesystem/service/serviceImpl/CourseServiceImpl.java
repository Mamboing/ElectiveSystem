package com.ap.electivesystem.service.serviceImpl;

import com.ap.electivesystem.entity.Course;
import com.ap.electivesystem.mapper.CourseMapper;
import com.ap.electivesystem.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}
