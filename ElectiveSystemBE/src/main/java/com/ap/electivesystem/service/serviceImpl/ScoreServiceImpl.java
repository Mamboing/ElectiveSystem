package com.ap.electivesystem.service.serviceImpl;

import com.ap.electivesystem.entity.Score;
import com.ap.electivesystem.mapper.ScoreMapper;
import com.ap.electivesystem.service.ScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

}
