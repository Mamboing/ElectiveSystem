package com.ap.electivesystem.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO implements Serializable {
    public static final int NO_LOGIN = -1;
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    public static final int ERROR_ROLE = 2;
    public static final int NO_PERMISSION = 3;
    public static final int INVALID_PARAMETER = 4;
    public static final int SERVER_ERROR = 10;
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    private Object data;

    public ResultVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultVO success(Object data) {
        return new ResultVO(0, "success", data);
    }

    public static ResultVO fail(Integer code, String message) {
        return new ResultVO(code, message);
    }

}
