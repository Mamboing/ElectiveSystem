package com.ap.electivesystem.dao;


import com.ap.electivesystem.entity.Student;
import com.ap.electivesystem.mapper.StudentMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class StudentDAO {

    @Resource
    private StudentMapper studentsMapper;

    public Student getByName(String userName){
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getStudentName, userName);
        return studentsMapper.selectOne(wrapper);
    }

}
