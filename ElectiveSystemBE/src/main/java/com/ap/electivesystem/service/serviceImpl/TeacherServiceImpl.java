package com.ap.electivesystem.service.serviceImpl;

import com.ap.electivesystem.entity.Teacher;
import com.ap.electivesystem.mapper.TeacherMapper;
import com.ap.electivesystem.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public Teacher getByName(String userName) {
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teacher::getTeacherName, userName);
        return teacherMapper.selectOne(wrapper);
    }

}
