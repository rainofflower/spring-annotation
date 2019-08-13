package com.atguigu.bean;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Order(2)//Ordered.HIGHEST_PRECEDENCE
@Component
public class IoOperationAspect2 {

    private AtomicInteger i = new AtomicInteger();

    @Before("execution(public * com.atguigu.bean.IoOperation.*(..))")
    public void beforeMethod(){
       ContextHolder.set(i.getAndIncrement());
       //System.out.println("前置方法2");
    }

    @After("execution(public * com.atguigu.bean.IoOperation.*(..))")
    public void afterMethod(){
        ContextHolder.get();
        //System.out.println("后置方法2");
    }
}
