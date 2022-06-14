package com.ap.electivesystem.service;

import com.ap.electivesystem.entity.Score;
import com.ap.electivesystem.entity.dto.ScoreDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ScoreService extends IService<Score> {

    int addScore(List<Score> list);

    PageInfo<ScoreDTO> find(String teacherName, String studentName, String courseName, int pageSize, int pageNo);

    void updateBatch(List<Score> scores);

}
