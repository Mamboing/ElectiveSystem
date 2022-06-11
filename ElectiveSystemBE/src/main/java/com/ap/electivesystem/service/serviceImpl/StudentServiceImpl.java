package com.ap.electivesystem.service.serviceImpl;

import com.ap.electivesystem.entity.Student;
import com.ap.electivesystem.mapper.StudentMapper;
import com.ap.electivesystem.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-06-07
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
