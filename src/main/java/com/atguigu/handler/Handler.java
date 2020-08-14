package com.atguigu.handler;

import java.lang.annotation.*;

/**
 * @author yanghui
 * @date 2020-08-13
 **/
@Target({ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Handler {

    BindPipelineInfo[] value();

    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    @interface BindPipelineInfo{

        Class<? extends CommandHandlerPipeline> pipeline();

        int order() default 0;
    }
}
