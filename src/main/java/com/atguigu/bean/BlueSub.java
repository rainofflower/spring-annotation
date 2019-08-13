package com.atguigu.bean;

import org.springframework.stereotype.Component;

/**
 * @author yanghui
 * @date 2019-8-13
 */
@Component
public class BlueSub extends Blue {

    @Calculate
    public void init(){
        super.init();
        System.out.println("blue...init...");
    }
}
