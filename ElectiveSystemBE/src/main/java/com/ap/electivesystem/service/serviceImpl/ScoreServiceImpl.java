package com.ap.electivesystem.service.serviceImpl;

import com.ap.electivesystem.entity.Score;
import com.ap.electivesystem.entity.dto.ScoreDTO;
import com.ap.electivesystem.mapper.ScoreMapper;
import com.ap.electivesystem.service.ScoreService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

    @Resource
    private ScoreMapper scoreMapper;

    @Override
    public int addScore(List<Score> list) {
        list.forEach(score -> score.setTotalGrade((int) (0.4 * score.getUsualGrade() + 0.6 * score.getFinalGrade())));
        return scoreMapper.insertBatch(list);
    }

    @Override
    public PageInfo<ScoreDTO> find(String teacherName, String studentName, String courseName, int pageSize, int pageNo) {
        PageHelper.startPage(pageNo, pageSize);
        List<ScoreDTO> search = scoreMapper.search(courseName, teacherName, studentName);
        return new PageInfo<>(search);
    }

    @Override
    public void updateBatch(List<Score> scores) {
        for (Score score : scores) {
            LambdaUpdateWrapper<Score> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(Score::getCourseId, score.getCourseId())
                    .eq(Score::getStudentId, score.getStudentId());
            scoreMapper.update(score, wrapper);
        }
    }
}
