package com.ap.electivesystem.mapper;

import com.ap.electivesystem.entity.Course;
import com.ap.electivesystem.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-06-07
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    List<Course> schedule(int student_id);
}
