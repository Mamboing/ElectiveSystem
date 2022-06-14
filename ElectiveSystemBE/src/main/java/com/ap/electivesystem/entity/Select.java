package com.ap.electivesystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_select")
public class Select implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("course_id")
    private Integer courseId;

    @TableField("student_id")
    private Integer studentId;

    @TableField("select_state")
    private Integer selectState;

}
