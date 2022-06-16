package com.ap.electivesystem.service.serviceImpl;

import com.ap.electivesystem.entity.Course;
import com.ap.electivesystem.entity.Teacher;
import com.ap.electivesystem.entity.bo.LoginStatusBO;
import com.ap.electivesystem.entity.vo.CourseVO;
import com.ap.electivesystem.mapper.TeacherMapper;
import com.ap.electivesystem.service.TeacherService;
import com.ap.electivesystem.utils.CopyUtil;
import com.ap.electivesystem.utils.SessionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private SessionUtil sessionUtil;
    @Resource
    private HttpSession session;
    @Resource
    private CopyUtil copyUtil;

    @Override
    public Teacher getByName(String userName) {
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teacher::getTeacherName, userName);
        return teacherMapper.selectOne(wrapper);
    }

    @Override
    public List<CourseVO> schedule() {
        LoginStatusBO loginStatus = sessionUtil.getLoginStatus(session);
        Integer id = loginStatus.getId();
        List<Course> schedule = teacherMapper.schedule(id);
        List<CourseVO> courseVOS = new ArrayList<>();
        schedule.forEach(course -> {
            courseVOS.add(copyUtil.courseCopy(course));
        });
        return courseVOS;
    }
}
