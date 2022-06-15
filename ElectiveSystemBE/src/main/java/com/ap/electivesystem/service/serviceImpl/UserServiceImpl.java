package com.ap.electivesystem.service.serviceImpl;

import com.ap.electivesystem.entity.bo.AuthInfoBO;
import com.ap.electivesystem.entity.bo.LoginStatusBO;
import com.ap.electivesystem.entity.constant.ReturnCode;
import com.ap.electivesystem.entity.constant.UserType;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.service.AdminService;
import com.ap.electivesystem.service.StudentService;
import com.ap.electivesystem.service.TeacherService;
import com.ap.electivesystem.service.UserService;
import com.ap.electivesystem.utils.Md5Encrypt;
import com.ap.electivesystem.utils.SessionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    public static final String PASSWORD_SALT = "Elective_System_0.0";

    @Resource
    private HttpSession session;
    @Resource
    private Md5Encrypt md5Encrypt;
    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private AdminService adminService;
    @Resource
    private SessionUtil sessionUtil;

    @Override
    public ResultVO login(String username, String password, Integer userType) {
        AuthInfoBO authInfoBO = getAuthInfoByUsername(username, userType);
        if (authInfoBO == null)
            return ResultVO.fail(ReturnCode.USER_NOT_EXIST);
        String passwordHash = md5Encrypt.encode(password, PASSWORD_SALT);
        if (!passwordHash.equals(authInfoBO.getPassword()))
            return ResultVO.fail(ReturnCode.PASSWORD_ERROR);
        LoginStatusBO statusBO = LoginStatusBO.fromAuthInfo(authInfoBO);
        sessionUtil.setLoginStatus(session, statusBO);
        return ResultVO.success(statusBO);

    }

    @Override
    public AuthInfoBO getAuthInfoByUsername(String userName, Integer userType) {
        if (userType == UserType.STUDENT)
            return AuthInfoBO.fromStudent(studentService.getByName(userName));
        else if (userType == UserType.TEACHER)
            return AuthInfoBO.fromTeacher(teacherService.getByName(userName));
        else if (userType == UserType.REGISTER)
            return AuthInfoBO.fromAdmin(adminService.getByName(userName));
        return null;
    }

}
