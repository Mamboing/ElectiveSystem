package com.ap.electivesystem.mapper;

import com.ap.electivesystem.entity.Course;
import com.ap.electivesystem.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    List<Course> schedule(int student_id);

}
