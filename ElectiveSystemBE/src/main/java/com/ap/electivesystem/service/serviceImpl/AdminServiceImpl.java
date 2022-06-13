package com.ap.electivesystem.service.serviceImpl;

import com.ap.electivesystem.entity.Admin;
import com.ap.electivesystem.mapper.AdminMapper;
import com.ap.electivesystem.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
