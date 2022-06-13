package com.ap.electivesystem.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO implements Serializable {

    private Integer courseId;

    private String courseName;

    private String weekday;

    private String time;

    private String teacherName;

    private String courseRoom;

    private String offerState;

}
