package com.ap.electivesystem.service.serviceImpl;

import com.ap.electivesystem.entity.bo.LoginStatusBO;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.manager.LoginStatusManager;
import com.ap.electivesystem.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class BaseServiceImpl implements BaseService {

    @Autowired
    private HttpSession session;
    @Autowired
    private LoginStatusManager loginStatusManager;

    @Override
    public LoginStatusBO getLoginStatus() {
        return loginStatusManager.getLoginStatus(session);
    }

    @Override
    public Integer getUserId() {
        return getLoginStatus().getId();
    }

    @Override
    public ResultVO result(Object data) {
        return new ResultVO(ResultVO.SUCCESS, "success", data);
    }

    @Override
    public ResultVO result(Object data, String message) {
        return new ResultVO(ResultVO.SUCCESS, message, data);
    }

    @Override
    public ResultVO failedResult(String message) {
        return new ResultVO(ResultVO.FAIL, message, null);
    }

    @Override
    public ResultVO failedResult(String message, Object data) {
        return new ResultVO(ResultVO.FAIL, message, data);
    }
}
