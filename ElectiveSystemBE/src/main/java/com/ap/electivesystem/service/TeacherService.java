package com.ap.electivesystem.service;

import com.ap.electivesystem.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

public interface TeacherService extends IService<Teacher> {

    Teacher getByName(String userName);

}
