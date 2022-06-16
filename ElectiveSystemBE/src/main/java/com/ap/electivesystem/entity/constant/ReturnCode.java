package com.ap.electivesystem.entity.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ReturnCode {

    COURSE_NOT_FOUND(1001, "没有找到对应的课表"),
    SCORE_NOT_FOUND(1002, "没有找到对应的成绩"),
    STUDENT_NOT_FOUND(1003, "没有找到对应的学生"),
    PAGE_PARAMETER_ERROR(1004, "分页参数问题"),
    ID_NULL_ERROR(1005, "ID为空"),
    UPDATE_ERROR(1006, "更新失败"),
    VERIFY_COURSE_ERROR(1007, "课程验证失败"),
    ILLEGAL_TIME_RANGE(1008, "不合法的时间范围"),
    SELECT_NOT_FOUND(1009, "选课信息未找到"),
    USER_NOT_EXIST(1010, "用户不存在"),
    PASSWORD_ERROR(1011, "密码错误"),
    NO_PERMISSION(1012, "无权限"),
    ERROR_ROLE(1013, "角色错误"),
    NO_LOGIN(1014, "您还没有登录"),
    TIME_CONFLICT(1015, "当前时间您已经有课！"),
    ROOM_FULLY_OCCUPIED(1016, "没有可用教室");

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
