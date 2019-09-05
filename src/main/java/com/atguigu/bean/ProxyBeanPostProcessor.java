package com.atguigu.bean;

import org.springframework.aop.SpringProxy;
import org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Component
public class ProxyBeanPostProcessor extends AspectJAwareAdvisorAutoProxyCreator {

    private AutowireCapableBeanFactory beanFactory;

    @Value("${io.thread}")
    private int threadNum;

    @Autowired
    private ThreadPoolTaskExecutor executor;


    public ProxyBeanPostProcessor(AutowireCapableBeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(beanClass == IoOperation.class){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(beanClass);
            enhancer.setInterfaces(new Class[]{SpringProxy.class});
            enhancer.setCallback((MethodInterceptor)(target, method, args, methodProxy) ->{
                if(method.getName().endsWith("operation")){
                    long start = System.currentTimeMillis();
                    //以下方法死循环
                    //method.invoke(target,args);
                    methodProxy.invokeSuper(target, args);
                    CountDownLatch countDownLatch = new CountDownLatch(threadNum);
                    for (int i = 0; i<threadNum; i++){
                        executor.execute(()->{
                            try {
                                methodProxy.invokeSuper(target, args);
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
                    return null;
                }
                return methodProxy.invokeSuper(target, args);
            });
            return postProcess(enhancer.create());
        }
        else{
            return super.postProcessBeforeInstantiation(beanClass,beanName);
        }
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
