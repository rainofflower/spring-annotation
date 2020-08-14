package com.atguigu.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * ${DESCRIPTION}
 *
 * @author yanghui
 * @date 2020-08-14 11:45
 **/
@Pipeline//("MyPipeline1")
public class MyPipeline1 extends CommandHandlerPipeline implements InitializingBean {

    @Autowired
    private MyHandler1 myHandler1;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.addLast("lalala",myHandler1);
    }

    @PostConstruct
    public void initMethod(){
        this.addFirst(myHandler1);
    }
}
