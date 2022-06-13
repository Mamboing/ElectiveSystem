package com.ap.electivesystem.service.serviceImpl;

import cn.hutool.core.util.RandomUtil;
import com.ap.electivesystem.entity.Course;
import com.ap.electivesystem.entity.bo.LoginStatusBO;
import com.ap.electivesystem.entity.vo.CourseVO;
import com.ap.electivesystem.manager.LoginStatusManager;
import com.ap.electivesystem.mapper.CourseMapper;
import com.ap.electivesystem.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    public static final int ROOM_START = 101;
    public static final int ROOM_END = 120;

    @Resource
    private LoginStatusManager loginStatusManager;
    @Resource
    private HttpSession session;
    @Resource
    private CourseMapper courseMapper;

    @Override
    public int saveNoId(String courseName, String weekday, String time) {
        LoginStatusBO loginStatus = loginStatusManager.getLoginStatus(session);
        Integer id = loginStatus.getId();
        Course course = new Course(courseName, weekday, time, id, RandomUtil.randomInt(ROOM_START, ROOM_END) + "", "0");
        return courseMapper.insert(course);
    }

    @Override
    public PageInfo<CourseVO> find(String courseName, String courseTime, String teacherName, int pageSize, int pageNo) {
        PageHelper.startPage(pageNo, pageSize);
        List<CourseVO> course = courseMapper.findCourse(courseName, courseTime, teacherName);
        return new PageInfo<>(course);
    }
}
