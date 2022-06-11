package com.ap.electivesystem.dao;


import com.ap.electivesystem.entity.Admin;
import com.ap.electivesystem.mapper.AdminMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AdminDAO {

    @Resource
    private AdminMapper adminMapper;

    public Admin getByName(String userName){
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getAdminName,userName);
        return adminMapper.selectOne(wrapper);
    }

}
