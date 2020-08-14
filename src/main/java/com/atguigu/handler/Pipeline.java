package com.atguigu.handler;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author yanghui
 * @date 2020-08-13
 **/
@Target({ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Pipeline {

    String value() default "";

    @AliasFor("value")
    String name() default "";
}
