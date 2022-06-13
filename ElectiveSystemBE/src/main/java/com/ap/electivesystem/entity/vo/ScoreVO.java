package com.ap.electivesystem.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreVO implements Serializable {
    private Integer courseId;

    private String courseName;

    private String teacherName;

    private Integer usualGrade;

    private Integer finalGrade;

    private Integer totalGrade;
}
