package com.ap.electivesystem.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentScore implements Serializable {

    private Integer studentId;

    private String studentName;

    private Integer courseId;

    private String courseName;

    private Integer usualGrade = 0;

    private Integer finalGrade = 0;

    private Integer totalGrade = 0;

}
