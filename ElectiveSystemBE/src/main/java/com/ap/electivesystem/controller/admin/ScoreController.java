package com.ap.electivesystem.controller.admin;

import com.ap.electivesystem.entity.Score;
import com.ap.electivesystem.entity.constant.ReturnCode;
import com.ap.electivesystem.entity.dto.ScoreDTO;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.service.ScoreService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 需要完成的功能：老师提交的成绩的审核、开始提交成绩、结束提交成绩
 */
//@Register(Register.SCORE_MANAGE)
@RestController
@RequestMapping("/admin/score")
public class ScoreController {

    @Resource
    private ScoreService scoreService;

    @GetMapping("/list")
    @ApiOperation("返回 dto.ScoreDTO 的列表，包装为PageInfo(其中含有属性total、list(即 dto.ScoreDTO 的列表)以及分页的参数)")
    @ApiResponses({
            @ApiResponse(code = 1004, message = "分页参数问题"),
            @ApiResponse(code = 0, message = "success")
    })
    public ResultVO list(@RequestParam(required = false) String teacherName, @RequestParam(required = false) String studentName, @RequestParam(required = false) String courseName, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "1") int pageNo) {
        if (pageSize < 0 || pageNo < 0)
            return ResultVO.fail(ReturnCode.PAGE_PARAMETER_ERROR);
        PageInfo<ScoreDTO> scoreDTOPageInfo = scoreService.find(teacherName, studentName, courseName, pageSize, pageNo);
        return ResultVO.success(scoreDTOPageInfo);
    }

    @PostMapping("/update")
    @ApiResponses({
            @ApiResponse(code = 1004, message = "分页参数问题"),
            @ApiResponse(code = 0, message = "success")
    })
    public ResultVO update(@RequestBody Score score) {
        LambdaUpdateWrapper<Score> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Score::getCourseId, score.getCourseId())
                .eq(Score::getStudentId, score.getStudentId());
        boolean update = scoreService.update(score, wrapper);
        if (update)
            return ResultVO.success(null);
        else return ResultVO.fail(ReturnCode.UPDATE_ERROR);
    }

    @PostMapping("/update/batch")
    public ResultVO updateBatch(@RequestBody List<Score> scores) {
        scoreService.updateBatch(scores);
        return ResultVO.success(null);
    }

}
