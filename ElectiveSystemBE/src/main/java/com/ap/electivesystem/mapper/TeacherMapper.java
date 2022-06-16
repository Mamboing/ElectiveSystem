package com.ap.electivesystem.mapper;

import com.ap.electivesystem.entity.Course;
import com.ap.electivesystem.entity.Teacher;
import com.ap.electivesystem.entity.vo.CourseVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    String getTeacherNameById(Integer courseId);

    List<CourseVO> schedule(Integer id);
}
