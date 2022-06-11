package com.ap.electivesystem.entity.bo;

import com.ap.electivesystem.entity.constant.UserType;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginStatusBO implements Serializable {

    private Boolean loggedIn = false;
    private Integer id;
    private String name;
    private Integer userType = UserType.NO;
    private Integer privilege = 0;

    public static LoginStatusBO fromAuthInfo(AuthInfoBO authInfo) {
        LoginStatusBO loginStatus = new LoginStatusBO();
        loginStatus.loggedIn = true;
        loginStatus.id = authInfo.getId();
        loginStatus.name = authInfo.getUsername();
        loginStatus.userType = authInfo.getUserType();
        loginStatus.privilege = authInfo.getPrivilege();
        return loginStatus;
    }

}
