package com.px.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface AppLogic {
    //为true时在Application的onCreate方法中最后执行
    boolean delay() default false;
    //优先级，值越大越先执行
    int priority() default 0;
}
