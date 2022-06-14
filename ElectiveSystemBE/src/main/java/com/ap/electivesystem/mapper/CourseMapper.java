package com.ap.electivesystem.mapper;

import com.ap.electivesystem.entity.Course;
import com.ap.electivesystem.entity.dto.CourseDTO;
import com.ap.electivesystem.entity.vo.CourseVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    String getCourseNameByCourseId(Integer courseId);

    List<CourseVO> findCourse(String courseName, String courseTime, String teacherName);

    List<CourseDTO> search(String courseName, String weekday, String time, String teacherName, String courseRoom, String offerState);

}
