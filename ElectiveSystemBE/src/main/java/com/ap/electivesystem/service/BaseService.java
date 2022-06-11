package com.ap.electivesystem.service;

import com.ap.electivesystem.entity.bo.LoginStatusBO;
import com.ap.electivesystem.entity.vo.ResultVO;

public interface BaseService {

    LoginStatusBO getLoginStatus();
    Integer getUserId();
    ResultVO result(Object data);
    ResultVO result(Object data, String message);
    ResultVO failedResult(String message);
    ResultVO failedResult(String message, Object data);

}
