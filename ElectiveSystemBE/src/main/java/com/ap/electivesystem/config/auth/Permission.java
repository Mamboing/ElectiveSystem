package com.ap.electivesystem.config.auth;

import com.ap.electivesystem.entity.constant.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Permission {
    private Integer userType = UserType.NO;
    private Integer permission = 0;
    private Boolean needLogin = true;

    public Permission(Integer userType) {
        this.userType = userType;
    }

    public Permission(Integer userType, Integer permission) {
        this.userType = userType;
        this.permission = permission;
    }

    public Permission(Boolean needLogin) {
        this.needLogin = needLogin;
    }
}
