package com.ap.electivesystem.mapper;

import com.ap.electivesystem.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    String getCourseNameByCourseId(Integer courseId);
}
