package com.atguigu.bean;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LogArgsAdvice implements MethodBeforeAdvice {

    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("准备执行方法：" + method.getName() + ",参数：" + Arrays.toString(args));
    }
}