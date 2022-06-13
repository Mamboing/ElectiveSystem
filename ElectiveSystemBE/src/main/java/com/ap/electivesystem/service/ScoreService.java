package com.ap.electivesystem.service;

import com.ap.electivesystem.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ScoreService extends IService<Score> {

    int addScore(List<Score> list);

}
