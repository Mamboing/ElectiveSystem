package com.ap.electivesystem.service.serviceImpl;

import com.ap.electivesystem.entity.Admin;
import com.ap.electivesystem.mapper.AdminMapper;
import com.ap.electivesystem.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin getByName(String userName) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getAdminName, userName);
        return adminMapper.selectOne(wrapper);
    }

}
