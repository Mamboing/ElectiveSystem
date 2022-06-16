package com.ap.electivesystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "course_id", type = IdType.AUTO)
    private Integer courseId;

    @TableField("course_name")
    private String courseName;

    @TableField("weekday")
    private String weekday;

    @TableField("time")
    private String time;

    @TableField("teacher_id")
    private Integer teacherId;

    @TableField("course_room")
    private String courseRoom;

    @TableField("offer_state")
    private String offerState;

    public Course(String courseName, String weekday, String time, Integer teacherId, String courseRoom, String offerState) {
        this.courseName = courseName;
        this.weekday = weekday;
        this.time = time;
        this.teacherId = teacherId;
        this.courseRoom = courseRoom;
        this.offerState = offerState;
    }
}
