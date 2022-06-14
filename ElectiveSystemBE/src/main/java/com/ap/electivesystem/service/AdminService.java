package com.ap.electivesystem.service;

import com.ap.electivesystem.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminService extends IService<Admin> {

    Admin getByName(String userName);

}
