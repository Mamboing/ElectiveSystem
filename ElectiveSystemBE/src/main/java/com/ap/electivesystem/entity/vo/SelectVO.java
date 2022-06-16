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

    private Integer teacherId;

    private String teacherName;

    private Integer studentId;

    private String studentName;

    private String weekday;

    private String courseRoom;

    private String time;

    private String offerState;
}
