package com.ap.electivesystem.service.serviceImpl;

import com.ap.electivesystem.entity.Course;
import com.ap.electivesystem.entity.Student;
import com.ap.electivesystem.entity.bo.LoginStatusBO;
import com.ap.electivesystem.entity.vo.CourseVO;
import com.ap.electivesystem.manager.LoginStatusManager;
import com.ap.electivesystem.mapper.StudentMapper;
import com.ap.electivesystem.service.StudentService;
import com.ap.electivesystem.utils.CopyUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    private LoginStatusManager loginStatusManager;
    @Resource
    private HttpSession session;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private CopyUtil copyUtil;

    @Override
    public List<CourseVO> schedule() {
        LoginStatusBO loginStatus = loginStatusManager.getLoginStatus(session);
        Integer id = loginStatus.getId();
        List<Course> schedule = studentMapper.schedule(id);
        List<CourseVO> courseVOS = new ArrayList<>();
        schedule.forEach(course -> {
            courseVOS.add(copyUtil.courseCopy(course));
        });
        return courseVOS;
    }
}
