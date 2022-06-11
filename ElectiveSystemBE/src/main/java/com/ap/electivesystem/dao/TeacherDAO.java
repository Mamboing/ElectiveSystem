package com.ap.electivesystem.dao;


import com.ap.electivesystem.entity.Teacher;
import com.ap.electivesystem.mapper.TeacherMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class TeacherDAO {

    @Resource
    private TeacherMapper teachersMapper;

    public Teacher getByName(String userName){
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teacher::getTeacherName,userName);
        return teachersMapper.selectOne(wrapper);
    }

}
