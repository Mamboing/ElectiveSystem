package com.ap.electivesystem.config.auth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Register {

    int NO = 0;

    int STUDENT_MANAGE = 1;

    int TEACHER_MANAGE = 2;

    int COURSE_MANAGE = 4;

    int SELECT_COURSE_MANAGE = 8;

    int ALL = 15;

    int value() default 0;

}
