package com.ap.electivesystem.entity.vo;

import com.ap.electivesystem.entity.constant.ReturnCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO implements Serializable {

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

    public static ResultVO fail(ReturnCode code) {
        return new ResultVO(code.getCode(), code.getMessage());
    }

}
