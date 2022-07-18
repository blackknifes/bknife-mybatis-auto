package com.bknife.mybatis.auto.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Table {
    /**
     * 表名
     * 
     * @return
     */
    public String name();

    /**
     * 表注释
     * 
     * @return
     */
    public String comment() default "";

    /**
     * 引擎
     * 
     * @return
     */
    public String engine() default "";

    /**
     * 字符集
     * 
     * @return
     */
    public String charset() default "";

    /**
     * 排序规则
     * 
     * @return
     */
    public String collate() default "";
}
