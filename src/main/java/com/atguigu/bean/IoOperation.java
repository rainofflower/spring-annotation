package com.atguigu.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Component
public class IoOperation {

    //@Value("${io.thread}")
    private int threadNum = 4;

    public void old(){
        long start = System.currentTimeMillis();
        for (int i = 0; i<threadNum; i++){
            Map param = new HashMap();
            param.put("num",i);
            operation(param);
        }
        System.out.println("old方法 消耗"+(System.currentTimeMillis()-start)+" 时间");
    }

    @Calculate
    public int operation(Map param){
        int num = Integer.parseInt(param.get("num").toString());
        System.out.println("执行计算");
        return ++num;
        //LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(5));
        //System.out.println("高耗时io处理完成");
    }

    public int m3(){
        return 1;
    }

}
