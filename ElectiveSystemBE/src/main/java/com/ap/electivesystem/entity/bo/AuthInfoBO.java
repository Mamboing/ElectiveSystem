package com.ap.electivesystem.entity.bo;

import com.ap.electivesystem.entity.Admin;
import com.ap.electivesystem.entity.Student;
import com.ap.electivesystem.entity.Teacher;
import com.ap.electivesystem.entity.constant.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthInfoBO {
    private Integer id;
    private String username;
    private String password;
    private Integer userType;
    private Integer privilege = 0;


    private AuthInfoBO(Integer id, String username, String password, Integer userType) {
        this(id, username, password, userType, 0);
    }

    private AuthInfoBO(Integer id, String username, String password, Integer userType, Integer permission) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.privilege = permission;
    }

    public static AuthInfoBO fromStudent(Student entity) {
        if (entity == null) {
            return null;
        }
        return new AuthInfoBO(entity.getStudentId(), entity.getStudentName(), entity.getStudentPass(), UserType.STUDENT);
    }

    public static AuthInfoBO fromTeacher(Teacher entity) {
        if (entity == null) {
            return null;
        }
        return new AuthInfoBO(entity.getTeacherId(), entity.getTeacherName(), entity.getTeacherPass(), UserType.TEACHER);
    }

    public static AuthInfoBO fromAdmin(Admin entity) {
        if (entity == null) {
            return null;
        }
        return new AuthInfoBO(entity.getAdminId(), entity.getAdminName(), entity.getAdminPass(), UserType.REGISTER,
                entity.getAdminPrivilege());
    }
}
