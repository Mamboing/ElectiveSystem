package com.ap.electivesystem.controller.admin;


import com.ap.electivesystem.config.auth.annotation.Register;
import com.ap.electivesystem.entity.Select;
import com.ap.electivesystem.entity.constant.ReturnCode;
import com.ap.electivesystem.entity.constant.TimeRange;
import com.ap.electivesystem.entity.dto.SelectDTO;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.entity.vo.SelectVO;
import com.ap.electivesystem.service.SelectService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 需要完成的功能：开始选课、结束选课、选课审核
 */
@Register(Register.SELECT_COURSE_MANAGE)
@RequestMapping("/admin/select/course/")
@RestController
public class SelectCourseController {

    @Resource
    private SelectService selectService;

    @PostMapping("/list")
    public ResultVO list(@RequestBody SelectDTO selectDTO, int pageSize, int pageNo) {
        if (pageNo < 0 || pageSize < 0)
            return ResultVO.fail(ReturnCode.PAGE_PARAMETER_ERROR);
        if (!TimeRange.legalTimeRange(selectDTO.getTime()))
            return ResultVO.fail(ReturnCode.ILLEGAL_TIME_RANGE);
        PageInfo<SelectVO> search = selectService.search(selectDTO, pageSize, pageNo);
        return ResultVO.success(search);
    }

    @PutMapping("/verify")
    public ResultVO verify(@RequestBody SelectDTO selectDTO) {
        Integer courseId = selectDTO.getCourseId();
        Integer studentId = selectDTO.getStudentId();
        if (courseId == null || studentId == null)
            return ResultVO.fail(ReturnCode.ID_NULL_ERROR);
        LambdaUpdateWrapper<Select> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Select::getStudentId, studentId)
                .eq(Select::getCourseId, courseId)
                .set(Select::getSelectState, "1");
        boolean update = selectService.update(wrapper);
        if (update)
            return ResultVO.success(null);
        else
            return ResultVO.fail(ReturnCode.SELECT_NOT_FOUND);
    }

    @PutMapping("/add/start")
    public ResultVO start() {
        return null;
    }

    @PutMapping("/add/finish")
    public ResultVO finish() {
        return null;
    }


}
