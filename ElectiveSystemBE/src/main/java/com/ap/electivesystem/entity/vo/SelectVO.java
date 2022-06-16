package com.ap.electivesystem.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectVO {

    private Integer courseId;

    private String courseName;

    private String teacherName;

    private String studentName;

    private String selectState;
}
