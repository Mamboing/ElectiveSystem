package com.ap.electivesystem.utils;

import com.ap.electivesystem.entity.bo.LoginStatusBO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionUtil {

    private static final String SESSION_USER_STATUS = "user_status";

    public LoginStatusBO getLoginStatus(HttpSession session) {
        LoginStatusBO loginStatus = (LoginStatusBO) session.getAttribute(SESSION_USER_STATUS);
        if (loginStatus == null) {
            loginStatus = new LoginStatusBO();
            setLoginStatus(session, loginStatus);
        }
        return loginStatus;
    }

    public void setLoginStatus(HttpSession session, LoginStatusBO loginStatus) {
        session.setAttribute(SESSION_USER_STATUS, loginStatus);
    }

}
