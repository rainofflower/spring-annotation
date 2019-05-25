package com;

import com.atguigu.bean.IoOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication(scanBasePackages = {"com.atguigu.bean"})
public class Application implements ApplicationListener<ContextRefreshedEvent>{

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    public void onApplicationEvent(ContextRefreshedEvent applicationEvent) {
        System.out.println("spring容器初始化完成");
        ApplicationContext applicationContext = applicationEvent.getApplicationContext();
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
        IoOperation ioOperation = applicationContext.getBean(IoOperation.class);
        ioOperation.operation();
        IoOperation ownIo = new IoOperation();
        ownIo.old();
    }
}
