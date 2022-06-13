package com.ap.electivesystem.mapper;

import com.ap.electivesystem.entity.Select;
import com.ap.electivesystem.entity.vo.StudentVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SelectMapper extends BaseMapper<Select> {

    int insertBatch(List<Integer> courseIds, Integer student_id);

    List<StudentVO> findByCourseId(Integer courseId);

}
