package com.bknife.mybatis.auto.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bknife.mybatis.auto.Where;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Not {
    public Where value() default Where.Equal;
}
