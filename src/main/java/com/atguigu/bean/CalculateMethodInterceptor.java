package com.atguigu.bean;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;

@Order(-2)
public class CalculateMethodInterceptor implements MethodInterceptor {

    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if(method.getName().endsWith("operation")){
            long start = System.currentTimeMillis();
            //以下方法死循环
            //method.invoke(target,args);
            methodProxy.invokeSuper(target, args);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            new Thread(()->{
                try{
                    methodProxy.invokeSuper(target, args);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            }).start();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("代理方法 消耗 "+(System.currentTimeMillis()-start)+" ms时间");
            return null;
        }
        return methodProxy.invokeSuper(target, args);
    }
}
