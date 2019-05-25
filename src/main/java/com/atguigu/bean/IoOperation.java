package com.atguigu.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Component
public class IoOperation {

    //@Value("${io.thread}")
    private int threadNum = 4;

    public void old(){
        long start = System.currentTimeMillis();
        for (int i = 0; i<threadNum; i++){
            operation();
        }
        System.out.println("old方法 消耗"+(System.currentTimeMillis()-start)+" 时间");
    }

    public void operation(){
        System.out.println("高耗时io开始执行");
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(5));
        System.out.println("高耗时io处理完成");
    }

}
