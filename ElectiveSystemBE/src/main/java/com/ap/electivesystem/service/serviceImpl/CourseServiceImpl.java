package com.ap.electivesystem.service.serviceImpl;

import com.ap.electivesystem.entity.Course;
import com.ap.electivesystem.entity.bo.LoginStatusBO;
import com.ap.electivesystem.entity.dto.CourseDTO;
import com.ap.electivesystem.entity.vo.CourseVO;
import com.ap.electivesystem.entity.vo.StudentVO;
import com.ap.electivesystem.mapper.CourseMapper;
import com.ap.electivesystem.mapper.SelectMapper;
import com.ap.electivesystem.service.CourseService;
import com.ap.electivesystem.utils.SessionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

//    @Resource
//    private HttpSession session;
    @Resource
    private CourseMapper courseMapper;
//    @Resource
//    private SessionUtil sessionUtil;
    @Resource
    private SelectMapper selectMapper;

    @Override
    public int saveNoId(String courseName, String weekday, String time, Integer id) {
//        LoginStatusBO loginStatus = sessionUtil.getLoginStatus(session);
//        Integer id = loginStatus.getId();
        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Course::getWeekday, weekday)
                .eq(Course::getTime, time)
                .eq(Course::getTeacherId, id);
        Long aLong = courseMapper.selectCount(wrapper);
        if (aLong > 0) return -1;
        int i;
        for (i = ROOM_START; i <= ROOM_END; i++) {
            wrapper.clear();
            wrapper.eq(Course::getWeekday, weekday)
                    .eq(Course::getTime, time)
                    .eq(Course::getCourseRoom, i + "");
            if (courseMapper.selectCount(wrapper) == 0) break;
        }
        if (i == ROOM_END + 1) return -2;
        Course course = new Course(courseName, weekday, time, id, i + "", "0");
        return courseMapper.insert(course);
    }

    @Override
    public PageInfo<CourseVO> find(String courseName, String courseTime, String teacherName, int pageSize, int pageNo, int studentId) {
        PageHelper.startPage(pageNo, pageSize);
        List<CourseVO> course = courseMapper.findCourse(courseName, courseTime, teacherName);
        List<Integer> courseIds = selectMapper.getCourseIds(studentId);
        for(int i = course.size(); i >= 0; i--){
            CourseVO courseVO = course.get(i);
            if(courseIds.contains(courseVO.getCourseId()))
                course.remove(courseVO);
        }
        return new PageInfo<>(course);
    }

    @Override
    public PageInfo<CourseDTO> search(String courseName, String weekday, String time, String teacherName, String courseRoom, String offerState, int pageSize, int pageNo) {
        PageHelper.startPage(pageNo, pageSize);
        List<CourseDTO> search = courseMapper.search(courseName, weekday, time, teacherName, courseRoom, offerState);
        return new PageInfo<>(search);
    }


    @Override
    public PageInfo<StudentVO> studentList(Integer courseId, int pageSize, int pageNo) {
        PageHelper.startPage(pageNo, pageSize);
        List<StudentVO> studentVOS = selectMapper.studentList(courseId);
        return new PageInfo<>(studentVOS);
    }
}
