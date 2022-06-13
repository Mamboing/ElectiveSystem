package com.ap.electivesystem.service;

import com.ap.electivesystem.entity.bo.AuthInfoBO;
import com.ap.electivesystem.entity.bo.LoginStatusBO;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.manager.LoginStatusManager;
import com.ap.electivesystem.manager.UserManager;
import com.ap.electivesystem.service.serviceImpl.BaseServiceImpl;
import com.ap.electivesystem.utils.Md5Encrypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class UserService extends BaseServiceImpl {

    public static final String PASSWORD_SALT = "Elective_System_0.0";

    @Resource
    private HttpSession session;
    @Resource
    private UserManager manager;
    @Resource
    private LoginStatusManager loginStatusManager;
    @Resource
    private Md5Encrypt md5Encrypt;

    public ResultVO login(String username, String password, Integer userType) {
        AuthInfoBO authInfoBO = manager.getAuthInfoByUsername(username, userType);
        if (authInfoBO == null)
            return failedResult("用户不存在");
        String passwordHash = computePasswordHash(password);
        if (!passwordHash.equals(authInfoBO.getPassword())) {
            return failedResult("密码错误");
        }

        LoginStatusBO statusBO = LoginStatusBO.fromAuthInfo(authInfoBO);
        loginStatusManager.setLoginStatus(session, statusBO);

        return result(statusBO);

    }

    public String computePasswordHash(String password) {
        String md5 = md5Encrypt.computeHexString(password);
        return md5Encrypt.computeHexString(md5 + PASSWORD_SALT);
    }

}
