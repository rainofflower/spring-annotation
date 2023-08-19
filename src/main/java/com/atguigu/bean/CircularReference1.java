package com.atguigu.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircularReference1 {

    @Autowired
    private CircularReference2 c2;

    public void test(){
        System.out.println("c1");
    }
}
