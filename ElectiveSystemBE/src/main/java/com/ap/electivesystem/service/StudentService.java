package com.ap.electivesystem.service;

import com.ap.electivesystem.entity.Student;
import com.ap.electivesystem.entity.vo.CourseVO;
import com.ap.electivesystem.entity.vo.ScoreVO;
import com.ap.electivesystem.entity.vo.StudentVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StudentService extends IService<Student> {
    List<CourseVO> schedule();

    List<ScoreVO> score();

    int insertSelect(Integer courseId);

    int insertSelectBatch(List<Integer> courseIds);

    int deleteSelect(Integer courseId);

    PageInfo<StudentVO> findByCourseId(Integer courseId, int pageSize, int pageNo);
}
