package com.bknife.mybatis.auto.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bknife.mybatis.auto.Type;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface Column {
    /**
     * 列名
     */
    public String name();

    /**
     * 类型
     * 
     * @return
     */
    public Type type() default Type.Auto;

    /**
     * 长度
     * 
     * @return
     */
    public int length() default 0;

    /**
     * 小数位数
     * 
     * @return
     */
    public int dot() default 0;

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

    /**
     * 默认值
     * 
     * @return
     */
    public String defaultValue() default "";

    /**
     * 是否允许为空
     * 
     * @return
     */
    public boolean nullable() default false;

    /**
     * 是否为主键
     * 
     * @return
     */
    public boolean primaryKey() default false;
}
