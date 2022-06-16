package com.ap.electivesystem.controller.admin;


import com.ap.electivesystem.config.auth.annotation.Register;
import com.ap.electivesystem.entity.constant.ReturnCode;
import com.ap.electivesystem.entity.constant.TimeRange;
import com.ap.electivesystem.entity.dto.SelectDTO;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.entity.vo.SelectVO;
import com.ap.electivesystem.service.SelectService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * 需要完成的功能：开始选课、结束选课、选课审核
 */
//@Register(Register.SELECT_COURSE_MANAGE)
@RequestMapping("/admin/select/course/")
@RestController
public class SelectCourseController {

    @Resource
    private SelectService selectService;

    @PostMapping("/list")
    @ApiOperation("返回 vo.SelectVO 的列表，包装为PageInfo(其中含有属性total、list(即 vo.SelectVO 的列表)以及分页的参数)")
    @ApiResponses({
            @ApiResponse(code = 1004, message = "分页参数问题"),
            @ApiResponse(code = 0, message = "success"),
            @ApiResponse(code = 1008, message = "不合法的时间范围"),
    })
    public ResultVO list(@RequestBody(required = false) SelectDTO selectDTO,@RequestParam(defaultValue = "10") int pageSize,@RequestParam(defaultValue = "1") int pageNo) {
        if (pageNo < 0 || pageSize < 0)
            return ResultVO.fail(ReturnCode.PAGE_PARAMETER_ERROR);
        if (selectDTO != null && selectDTO.getTime().length() != 0 && !TimeRange.legalTimeRange(selectDTO.getTime()) )
            return ResultVO.fail(ReturnCode.ILLEGAL_TIME_RANGE);
        PageInfo<SelectVO> search = selectService.search(selectDTO, pageSize, pageNo);
        return ResultVO.success(search);
    }

//    @PutMapping("/verify")
//    @ApiResponses({
//            @ApiResponse(code = 1005, message = "ID为空"),
//            @ApiResponse(code = 0, message = "success"),
//            @ApiResponse(code = 1009, message = "选课信息未找到"),
//    })
//    public ResultVO verify(@RequestBody SelectDTO selectDTO) {
//        Integer courseId = selectDTO.getCourseId();
//        Integer studentId = selectDTO.getStudentId();
//        if (courseId == null || studentId == null)
//            return ResultVO.fail(ReturnCode.ID_NULL_ERROR);
//        LambdaUpdateWrapper<Select> wrapper = new LambdaUpdateWrapper<>();
//        wrapper.eq(Select::getStudentId, studentId)
//                .eq(Select::getCourseId, courseId);
//        boolean update = selectService.update(wrapper);
//        if (update)
//            return ResultVO.success(null);
//        else
//            return ResultVO.fail(ReturnCode.SELECT_NOT_FOUND);
//    }

    @PutMapping("/add/start")
    @ApiIgnore
    public ResultVO start() {
        return null;
    }

    @PutMapping("/add/finish")
    @ApiIgnore
    public ResultVO finish() {
        return null;
    }


}
