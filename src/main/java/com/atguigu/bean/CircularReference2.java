package com.atguigu.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircularReference2 {

    @Autowired
    private CircularReference1 c1;

    public void test(){
        System.out.println("c2");
    }
}
