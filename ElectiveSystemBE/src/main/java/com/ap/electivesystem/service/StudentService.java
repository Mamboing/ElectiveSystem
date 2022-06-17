package com.ap.electivesystem.service;

import com.ap.electivesystem.entity.Student;
import com.ap.electivesystem.entity.vo.CourseVO;
import com.ap.electivesystem.entity.vo.ScoreVO;
import com.ap.electivesystem.entity.vo.StudentScore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StudentService extends IService<Student> {
    List<CourseVO> schedule(Integer id);

    List<ScoreVO> score(Integer id);

    int insertSelect(Integer courseId, Integer id);

    int insertSelectBatch(List<Integer> courseIds, Integer id);

    int deleteSelect(Integer courseId, Integer id);

    PageInfo<StudentScore> findByCourseId(Integer courseId, int pageSize, int pageNo, int teacherId, Integer studentId, String studentName);

    Student getByName(String userName);
}
