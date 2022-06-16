package com.ap.electivesystem.controller.admin;

import com.ap.electivesystem.config.auth.annotation.Register;
import com.ap.electivesystem.entity.Admin;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@Register
@RestController
@RequestMapping("/admin")
@ApiIgnore
public class AdminController {

    @Resource
    private AdminService adminService;

    @GetMapping("/list")
    public ResultVO list() {
        List<Admin> list = adminService.list();
        return ResultVO.success(list);
    }

}
