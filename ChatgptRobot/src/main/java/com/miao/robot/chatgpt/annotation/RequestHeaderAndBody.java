package com.miao.robot.chatgpt.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestHeaderAndBody {
    boolean required() default true;
}
