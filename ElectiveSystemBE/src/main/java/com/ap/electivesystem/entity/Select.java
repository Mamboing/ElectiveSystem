package com.ap.electivesystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@TableName("tb_select")
public class Select implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("course_id")
    private Integer courseId;

    @TableField("student_id")
    private Integer studentId;

    @TableField("select_state")
    private String selectState;


}
