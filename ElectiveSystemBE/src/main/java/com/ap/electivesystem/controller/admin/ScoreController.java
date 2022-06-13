package com.ap.electivesystem.controller.admin;

import com.ap.electivesystem.config.auth.annotation.Register;
import com.ap.electivesystem.entity.Course;
import com.ap.electivesystem.entity.Score;
import com.ap.electivesystem.entity.constant.ReturnCode;
import com.ap.electivesystem.entity.dto.CourseDTO;
import com.ap.electivesystem.entity.vo.ResultVO;
import com.ap.electivesystem.service.ScoreService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 需要完成的功能：老师提交的成绩的审核、开始提交成绩、结束提交成绩
 */
@Register(Register.SCORE_MANAGE)
@RestController
@RequestMapping("/admin/score")
public class ScoreController {

//    @Resource
//    private ScoreService scoreService;

//    @GetMapping("/list")
//    public ResultVO list(@RequestParam(required = false) String courseName, @RequestParam(required = false) String weekday, @RequestParam(required = false) String time, @RequestParam(required = false) String teacherName, @RequestParam(required = false) String courseRoom, @RequestParam(required = false) String offerState, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "1") int pageNo){
//        if(pageNo < 0 || pageSize < 0)
//            return ResultVO.fail(ReturnCode.PAGE_PARAMETER_ERROR);
//        PageInfo<CourseDTO> search = courseService.search(courseName, weekday, time, teacherName, courseRoom, offerState, pageSize, pageNo);
//        return ResultVO.success(search);
//    }
//
//    @PostMapping("/update")
//    public ResultVO update(@RequestBody Score score){
//        LambdaUpdateWrapper<Score> wrapper = new LambdaUpdateWrapper<>();
//        wrapper.eq(Score::getStudentId,score.getStudentId())
//                .eq(Score::getCourseId, score.getCourseId());
//        return ResultVO.success(scoreService.update(wrapper));
//    }
//
//    @PostMapping("/update/batch")
//    public ResultVO updateBatch(@RequestBody List<Score> scores){
//        scores.forEach(score -> {
//            LambdaUpdateWrapper<Score> wrapper = new LambdaUpdateWrapper<>();
//            wrapper.eq(Score::getStudentId,score.getStudentId())
//                    .eq(Score::getCourseId, score.getCourseId());
//            scoreService.update(wrapper);
//        });
//        return ResultVO.success(null);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResultVO delete(@PathVariable Integer id){
//        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(Course::getCourseId, id);
//        return ResultVO.success(courseService.remove(wrapper));
//    }
//
//    @PostMapping("/delete/batch")
//    public ResultVO delete(@RequestBody List<Integer> id){
//        return ResultVO.success(courseService.removeBatchByIds(id));
//    }
//
//    @PutMapping("/add/start")
//    public ResultVO start(){
//        return null;
//    }
//
//    @PutMapping("/add/finish")
//    public ResultVO finish(){
//        return null;
//    }

}
