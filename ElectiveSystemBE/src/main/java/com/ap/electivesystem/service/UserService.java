package com.ap.electivesystem.service;

import com.ap.electivesystem.entity.bo.AuthInfoBO;
import com.ap.electivesystem.entity.vo.ResultVO;

public interface UserService {

    ResultVO login(String username, String password, Integer userType);

    AuthInfoBO getAuthInfoByUsername(String userName, Integer userType);

}
