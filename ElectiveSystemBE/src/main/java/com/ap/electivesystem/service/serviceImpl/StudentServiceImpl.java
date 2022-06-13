package com.ap.electivesystem.service.serviceImpl;

import com.ap.electivesystem.entity.Course;
import com.ap.electivesystem.entity.Score;
import com.ap.electivesystem.entity.Select;
import com.ap.electivesystem.entity.Student;
import com.ap.electivesystem.entity.bo.LoginStatusBO;
import com.ap.electivesystem.entity.vo.CourseVO;
import com.ap.electivesystem.entity.vo.ScoreVO;
import com.ap.electivesystem.entity.vo.StudentVO;
import com.ap.electivesystem.manager.LoginStatusManager;
import com.ap.electivesystem.mapper.*;
import com.ap.electivesystem.service.StudentService;
import com.ap.electivesystem.utils.CopyUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    private ScoreMapper scoreMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private SelectMapper selectMapper;
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

    @Override
    public List<ScoreVO> score() {
        LoginStatusBO loginStatus = loginStatusManager.getLoginStatus(session);
        Integer id = loginStatus.getId();
        LambdaQueryWrapper<Score> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Score::getStudentId, id);
        List<Score> scores = scoreMapper.selectList(lambdaQueryWrapper);
        ArrayList<ScoreVO> scoreVOS = new ArrayList<>();
        for (Score score : scores) {
            Integer courseId = score.getCourseId();
            scoreVOS.add(new ScoreVO(courseId, courseMapper.getCourseNameByCourseId(courseId), teacherMapper.getTeacherNameById(courseId), score.getUsualGrade(), score.getFinalGrade(), score.getTotalGrade()));
        }
        return scoreVOS;
    }

    @Override
    public int insertSelect(Integer courseId) {
        LoginStatusBO loginStatus = loginStatusManager.getLoginStatus(session);
        Integer id = loginStatus.getId();
        Select select = new Select(courseId, id, 0);
        return selectMapper.insert(select);
    }

    @Override
    public int insertSelectBatch(List<Integer> courseIds) {
        LoginStatusBO loginStatus = loginStatusManager.getLoginStatus(session);
        Integer id = loginStatus.getId();
        return selectMapper.insertBatch(courseIds, id);
    }

    @Override
    public int deleteSelect(Integer courseId) {
        LoginStatusBO loginStatus = loginStatusManager.getLoginStatus(session);
        Integer id = loginStatus.getId();
        LambdaQueryWrapper<Select> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Select::getCourseId, courseId).eq(Select::getStudentId, id);
        return selectMapper.delete(wrapper);
    }

    @Override
    public List<StudentVO> findByCourseId(Integer courseId) {
        return selectMapper.findByCourseId(courseId);
    }
}
