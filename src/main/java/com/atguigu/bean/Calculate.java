package com.atguigu.bean;

import java.lang.annotation.*;

/**
 * @author yanghui
 * @date 2019-8-13
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Calculate {
}
