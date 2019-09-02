package com.atguigu.bean;

import org.springframework.aop.SpringProxy;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

//@Component
public class ProxyAspectCreator extends AspectJAwareAdvisorAutoProxyCreator {

    private AutowireCapableBeanFactory beanFactory;

    @Value("${io.thread}")
    private int threadNum;

    @Autowired
    private ThreadPoolTaskExecutor executor;

    public ProxyAspectCreator(AutowireCapableBeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(beanClass == IoOperation.class ){
            return new IoOperationSub();
        }
        return null;
    }

    public <T> T postProcess(T object){
        if(object == null){
            return null;
        }
//        T result;
        beanFactory.autowireBean(object);
//        String s = object.toString();
//        result = (T) beanFactory.initializeBean(object, s);
        return object;
    }

    class IoOperationSub extends IoOperation{
        public int operation(Map param){
            long start = System.currentTimeMillis();
            //以下方法死循环
            //method.invoke(target,args);
            int r = super.operation(param);
            CountDownLatch countDownLatch = new CountDownLatch(threadNum);
            for (int i = 0; i<threadNum; i++){
                executor.execute(()->{
                    try {
                        super.operation(param);
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
            System.out.println("代理方法 消耗 "+(System.currentTimeMillis()-start)+" ms时间");
            return r;
        }
    }
}
