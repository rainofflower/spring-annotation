package com.atguigu.bean;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author yanghui
 * @date 2019-8-13
 */
@Aspect
@Order(1)//Ordered.HIGHEST_PRECEDENCE
//@Component
public class CalculateAspect {

    @Value("${io.thread}")
    private int threadNum;

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Around("@annotation(calculate)")
    public Object doCalculate(ProceedingJoinPoint joinPoint , Calculate calculate){
        Object result = null;
        try{
            Object[] args = joinPoint.getArgs();
            Map param = (Map) args[0];
            Object r1 = joinPoint.proceed(args);
            System.out.println("执行结果："+r1);

//            param.put("num",2);
//            Object r2 = joinPoint.proceed(new Object[]{param});
//            System.out.println("执行结果："+r2);
            CountDownLatch countDownLatch = new CountDownLatch(threadNum);
            for (int i = 0; i<threadNum; i++){
                executor.execute(()->{
                    try {
                        Map<Object, Object> p = new HashMap<>();
                        p.putAll(param);
                        p.put("num",2);
                        Object r2 = joinPoint.proceed(new Object[]{p});
                        System.out.println("执行结果："+r2);
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }finally {
                        countDownLatch.countDown();
                    }
                });
            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = r1;
        }catch (Throwable throwable){
            //
        }
        return result;
    }
}
