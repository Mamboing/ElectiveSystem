package com.ap.electivesystem.service.serviceImpl;

import com.ap.electivesystem.entity.Admin;
import com.ap.electivesystem.mapper.AdminMapper;
import com.ap.electivesystem.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-06-07
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
