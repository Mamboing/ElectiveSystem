package com.ap.electivesystem.service.serviceImpl;

import com.ap.electivesystem.entity.Select;
import com.ap.electivesystem.entity.dto.SelectDTO;
import com.ap.electivesystem.entity.vo.SelectVO;
import com.ap.electivesystem.mapper.SelectMapper;
import com.ap.electivesystem.service.SelectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SelectServiceImpl extends ServiceImpl<SelectMapper, Select> implements SelectService {

    @Resource
    private SelectMapper selectMapper;

    @Override
    public PageInfo<SelectVO> search(SelectDTO selectDTO, int pageSize, int pageNo) {
        PageHelper.startPage(pageNo, pageSize);
        List<SelectVO> search = selectMapper.search(selectDTO);
        return new PageInfo<>(search);
    }

}
