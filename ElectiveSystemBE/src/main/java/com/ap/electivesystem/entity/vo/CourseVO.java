package com.ap.electivesystem.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseVO implements Serializable {
    private Integer courseId;

    private String courseName;

    private String weekday;

    private String time;

    private String teacherName;

    private String courseRoom;

    private String offerState;
}
