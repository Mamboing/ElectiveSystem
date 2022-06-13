package com.ap.electivesystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_admin")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("admin_id")
    private Integer adminId;

    @TableField("admin_name")
    private String adminName;

    @TableField("admin_pass")
    private String adminPass;

    @TableField("admin_privilege")
    private Integer adminPrivilege;


}
