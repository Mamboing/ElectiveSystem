package com.ap.electivesystem;

import com.ap.electivesystem.mapper.TeacherMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ElectiveSystemApplicationTests {

    @Resource
    private TeacherMapper teacherMapper;

    @Test
    void contextLoads() {
        String teacherNameById = teacherMapper.getTeacherNameById(400002);
        System.out.println(teacherNameById);
    }

}
