package com.atguigu.bean;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
//@Component
public class IoOperationAspect1 {

   @Before("execution(public * com.atguigu.bean.IoOperation.*(..))")
    public void beforeMethod(){
       System.out.println("前置方法");
   }

    @After("execution(public * com.atguigu.bean.IoOperation.*(..))")
    public void afterMethod(){
        System.out.println("后置方法");
    }
}
