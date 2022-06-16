package com.ap.electivesystem.controller.admin;

import com.ap.electivesystem.config.auth.annotation.Register;
import com.ap.electivesystem.entity.Admin;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.service.AdminService;
import com.ap.electivesystem.utils.Md5Encrypt;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@Register
@RestController
@RequestMapping("/admin")
public class AdminController {

    public static final String PASSWORD_SALT = "Elective_System_0.0";

    @Resource
    private AdminService adminService;
    @Resource
    private Md5Encrypt md5Encrypt;


    @GetMapping("/list")
    public ResultVO list() {
        List<Admin> list = adminService.list();
        return ResultVO.success(list);
    }

    @PostMapping("/update")
    public ResultVO update(@RequestBody Admin admin){
        admin.setAdminPass(md5Encrypt.encode(admin.getAdminPass(), PASSWORD_SALT));
        return ResultVO.success(adminService.updateById(admin));
    }

}
