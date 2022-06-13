package com.ap.electivesystem.entity.vo;

import com.ap.electivesystem.entity.constant.UserType;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotNull()
    @Range(min = UserType.STUDENT, max = UserType.REGISTER, message = "无效的用户类型")
    private Integer userType;

}
