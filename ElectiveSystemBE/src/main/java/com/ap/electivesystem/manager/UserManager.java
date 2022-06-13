package com.ap.electivesystem.manager;

import com.ap.electivesystem.dao.AdminDAO;
import com.ap.electivesystem.dao.StudentDAO;
import com.ap.electivesystem.dao.TeacherDAO;
import com.ap.electivesystem.entity.bo.AuthInfoBO;
import com.ap.electivesystem.entity.constant.UserType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserManager extends BaseManager {

    @Resource
    private StudentDAO studentDAO;
    @Resource
    private TeacherDAO teacherDAO;
    @Resource
    private AdminDAO adminDAO;


    public AuthInfoBO getAuthInfoByUsername(String userName, Integer userType) {
        if (userType == UserType.STUDENT)
            return AuthInfoBO.fromStudent(studentDAO.getByName(userName));
        else if (userType == UserType.TEACHER)
            return AuthInfoBO.fromTeacher(teacherDAO.getByName(userName));
        else if (userType == UserType.REGISTER)
            return AuthInfoBO.fromAdmin(adminDAO.getByName(userName));
        return null;
    }

}
