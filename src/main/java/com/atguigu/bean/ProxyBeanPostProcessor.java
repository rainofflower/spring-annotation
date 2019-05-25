package com.atguigu.bean;

import org.springframework.aop.SpringProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Component
public class ProxyBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    private AutowireCapableBeanFactory beanFactory;

    @Value("${io.thread}")
    private int threadNum;

    @Autowired
    private ThreadPoolTaskExecutor executor;


    public ProxyBeanPostProcessor(AutowireCapableBeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    public Object postProcessBeforeInstantiation(Class<?> aClass, String s) throws BeansException {
        if(aClass == IoOperation.class){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(aClass);
            enhancer.setInterfaces(new Class[]{SpringProxy.class});
            enhancer.setCallback((MethodInterceptor)(target, method, args, methodProxy) ->{
                if(method.getName().endsWith("operation")){
                    long start = System.currentTimeMillis();
                    CountDownLatch countDownLatch = new CountDownLatch(threadNum);
                    for (int i = 0; i<threadNum; i++){
                        executor.execute(()->{
                            try {
                                methodProxy.invokeSuper(target, args);
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                            }
                            countDownLatch.countDown();
                        });
                    }
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("代理方法 消耗"+(System.currentTimeMillis()-start)+" 时间");
                    return null;
                }
                return methodProxy.invokeSuper(target, args);
            });
            return postProcess(enhancer.create());
        }
        return null;
    }

    public boolean postProcessAfterInstantiation(Object o, String s) throws BeansException {
        return true;
    }

    public PropertyValues postProcessPropertyValues(PropertyValues propertyValues, PropertyDescriptor[] propertyDescriptors, Object o, String s) throws BeansException {
        return propertyValues;
    }

    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }

    public <T> T postProcess(T object){
        if(object == null){
            return null;
        }
        T result;
        beanFactory.autowireBean(object);
        String s = object.toString();
        result = (T) beanFactory.initializeBean(object, s);
        return result;
    }
}
