package com.ap.electivesystem.mapper;

import com.ap.electivesystem.entity.Score;
import com.ap.electivesystem.entity.dto.ScoreDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapper extends BaseMapper<Score> {

    int insertBatch(List<Score> list);

    List<ScoreDTO> search(String courseName, String teacherName, String studentName);

}
