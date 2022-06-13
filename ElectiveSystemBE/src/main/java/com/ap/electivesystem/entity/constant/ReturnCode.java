package com.ap.electivesystem.entity.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ReturnCode {

    COURSE_NOT_FOUND(1001, "没有找到对应的课表"),
    SCORE_NOT_FOUND(1002, "没有找到对应的成绩"),
    STUDENT_NOT_FOUND(1003, "没有找到对应的学生"),
    PAGE_PARAMETER_ERROR(1004, "分页参数问题"),
    ID_NULL_ERROR(1005,"ID为空");

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
