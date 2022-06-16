package com.ap.electivesystem.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectDTO {

    private Integer courseId;

    private Integer teacherId;

    private Integer studentId;

    private String teacherName;

    private String courseName;

    private String studentName;

    private String weekday;

    private String courseRoom;

    private String time;

    private String offerState;

}
