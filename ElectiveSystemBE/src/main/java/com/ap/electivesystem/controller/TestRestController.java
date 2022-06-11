package com.ap.electivesystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestRestController {
    @RequestMapping("/getSessionId")
    public Map<String, Object> getSessionId(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>(4);

        HttpSession session = request.getSession();
        String id = session.getId();

        int port = request.getServerPort();
        map.put("sessionId", id);
        map.put("serverPort", port);

        return map;
    }
}
