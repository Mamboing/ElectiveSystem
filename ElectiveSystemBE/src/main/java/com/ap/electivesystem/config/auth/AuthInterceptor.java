package com.ap.electivesystem.config.auth;

import com.ap.electivesystem.entity.bo.LoginStatusBO;
import com.ap.electivesystem.entity.constant.HttpStatusCode;
import com.ap.electivesystem.entity.constant.ReturnCode;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private PermissionScanner scanner;
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private SessionUtil sessionUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        Permission permission = scanner.scan(method);
        if (!permission.getNeedLogin() || permission.getUserType().equals(0))
            return true;
        LoginStatusBO loginStatus = sessionUtil.getLoginStatus(request.getSession());
        if (!loginStatus.getLoggedIn()) {
            noLogin(response);
            return false;
        }
        if (!loginStatus.getUserType().equals(permission.getUserType())) {
            errorRole(response);
            return false;
        }
        if ((loginStatus.getPrivilege() & permission.getPermission()) != permission.getPermission()) {
            noPermission(response);
            return false;
        }

        return true;
    }

    private void noLogin(HttpServletResponse response) {
        response.setStatus(HttpStatusCode.UNAUTHORIZED);
        ResultVO resultVO = ResultVO.fail(ReturnCode.NO_LOGIN);
        sendResult(resultVO, response);
    }

    private void errorRole(HttpServletResponse response) {
        response.setStatus(HttpStatusCode.FORBIDDEN);
        ResultVO resultVO = ResultVO.fail(ReturnCode.ERROR_ROLE);
        sendResult(resultVO, response);
    }

    private void noPermission(HttpServletResponse response) {
        response.setStatus(HttpStatusCode.FORBIDDEN);
        ResultVO resultVO = ResultVO.fail(ReturnCode.NO_PERMISSION);
        sendResult(resultVO, response);
    }

    private void sendResult(ResultVO result, HttpServletResponse response) {
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");

        try (Writer writer = response.getWriter()) {
            writer.write(objectMapper.writeValueAsString(result));
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
