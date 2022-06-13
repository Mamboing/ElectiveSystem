package com.ap.electivesystem.utils;

import com.ap.electivesystem.entity.Course;
import com.ap.electivesystem.entity.Teacher;
import com.ap.electivesystem.entity.vo.CourseVO;
import com.ap.electivesystem.mapper.TeacherMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CopyUtil {

    @Resource
    private TeacherMapper teacherMapper;

    public CourseVO courseCopy(Course course) {
        Integer teacherId = course.getTeacherId();
        Teacher teacher = teacherMapper.selectById(teacherId);
        return new CourseVO(course.getCourseId(), course.getCourseName(), course.getWeekday(), course.getTime(), teacher.getTeacherName(), course.getCourseRoom());
    }

}
