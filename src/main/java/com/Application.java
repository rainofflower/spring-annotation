package com;

import com.atguigu.bean.BlueSub;
import com.atguigu.bean.IoOperation;
import org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication(scanBasePackages = {"com.atguigu.bean"})
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @EventListener(classes = ContextRefreshedEvent.class)
    public void onApplicationEvent(ApplicationContextEvent applicationEvent) {
        System.out.println("spring容器初始化完成");
        ApplicationContext applicationContext = applicationEvent.getApplicationContext();
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
//        BlueSub bean = applicationContext.getBean(BlueSub.class);
//        bean.init();
        IoOperation ioOperation = applicationContext.getBean(IoOperation.class);
        Map<Object, Object> param = new HashMap<>();
        param.put("num",1);
//        CountDownLatch countDownLatch = new CountDownLatch(2);
//        for (int i = 0; i<2; i++){
//            executor.execute(()->{
//                try{
//                    param.put("num",1);
//                    ioOperation.operation(param);
//                }
//                catch (Throwable throwable){
//                    //
//                }
//                finally {
//                    countDownLatch.countDown();
//                }
//            });
//        }
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            //
//        }
        ioOperation.operation(param);
//        IoOperation ownIo = new IoOperation();
//        ownIo.old();
    }
}
