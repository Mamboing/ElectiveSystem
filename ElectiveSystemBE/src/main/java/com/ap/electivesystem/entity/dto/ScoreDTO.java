package com.ap.electivesystem.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {

    private String courseName;

    private String courseId;

    private String teacherName;

    private String studentName;

    private Integer usualGrade;

    private Integer finalGrade;

    private Integer totalGrade;

}
