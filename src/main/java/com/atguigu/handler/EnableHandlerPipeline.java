package com.atguigu.handler;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 * @author yanghui
 * @date 2020-08-14
 **/
@Target({ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({PipelineRegister.class,PipelineBeanPostProcessor.class})
public @interface EnableHandlerPipeline {

    /**
     * 默认为使用注解的类 所在包
     */
    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};
}
