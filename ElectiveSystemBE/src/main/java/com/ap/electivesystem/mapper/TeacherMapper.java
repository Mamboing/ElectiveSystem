package com.ap.electivesystem.mapper;

import com.ap.electivesystem.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    String getTeacherNameById(Integer courseId);
}
