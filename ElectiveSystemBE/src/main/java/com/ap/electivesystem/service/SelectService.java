package com.ap.electivesystem.service;

import com.ap.electivesystem.entity.Select;
import com.ap.electivesystem.entity.dto.SelectDTO;
import com.ap.electivesystem.entity.vo.SelectVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

public interface SelectService extends IService<Select> {

    PageInfo<SelectVO> search(SelectDTO selectDTO, int pageSize, int pageNo);

}
