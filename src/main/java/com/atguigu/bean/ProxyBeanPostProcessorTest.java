package com.atguigu.bean;

import org.springframework.aop.SpringProxy;
import org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

public class ProxyBeanPostProcessorTest extends InstantiationAwareBeanPostProcessorAdapter {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    /**
     * 执行postProcessBeforeInstantiation方法若返回bean，则直接跳过 beanFactory 的 resolveBeforeInstantiation 方法中
     * 后续的InstantiationAwareBeanPostProcessor，直接来到beanFactory的applyBeanPostProcessorsAfterInitialization方法中
     * 执行所有BeanPostProcessor的postProcessAfterInitialization方法，然后就返回bean。
     * 而普通bean(包括springAOP自动创建代理的bean ！)创建流程是先执行 resolveBeforeInstantiation方法，但并不会返回bean，
     * 然后执行beanFactory的 doCreateBean方法，创建实例(createBeanInstance方法)，注入属性(populateBean方法),
     * 初始化bean(执行initializeBean方法，方法内applyBeanPostProcessorsBeforeInitialization->invokeInitMethods->applyBeanPostProcessorsAfterInitialization)
     * 继续后续流程，最后返回bean
     *
     * 注意点
     * 1：默认情况下 SpringAOP 自动创建代理的地方是在 postProcessAfterInitialization 方法里而不是 postProcessBeforeInstantiation 方法，
     * 对于增强后的方法，spring会在调用目标方法时执行通知链，对于未增强的方法，spring会使用被增强的实例内部引用的未增强前的实例的方法。
     * 即被增强的实例内部会有一个 spring 实例化的未增强的target bean(其实就是调用postProcessAfterInitialization创建代理之前已经走完 resolveBeforeInstantiation，
     * createBeanInstance，populateBean，applyBeanPostProcessorsBeforeInitialization，invokeInitMethods这些流程的一个bean
     *
     * 2：AutowiredAnnotationBeanPostProcessor注入属性的时机是在 beanFactory 的 populateBean 方法中通过回调 postProcessPropertyValues方法完成的
     *
     * 3:若使用以下方法生成代理的同时被代理的类也在SpringAOP管理下，会出现AOP方法重复执行一次的问题，并且必须保证SpringAOP使用cglib的方式生成代理，
     *  否则生成的代理既无法autowire by name也无法autowire by type，即生成的bean不方便取出使用
     */
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(beanClass == MessageService2.class){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(beanClass);
            enhancer.setInterfaces(new Class[]{SpringProxy.class});
            enhancer.setCallback((MethodInterceptor)(target, method, args, methodProxy) ->{
                if(method.getName().endsWith("setMessage")){
                    System.out.println("执行代理方法");
                    return methodProxy.invokeSuper(target, args);
                }
                return methodProxy.invokeSuper(target, args);
            });
//            return enhancer.create();
            /**
             * 若直接执行上方的enhancer.create(),spring初始化bean流程中的populateBean方法和initializeBean方法会直接跳过，
             * 所以属性注入等依赖于populateBean方法和initializeBean方法的行为都不生效，
             * 若要补充执行属性注入和初始化方法，可以手动调用beanFactory的autowireBean和initializeBean方法，即下方postProcess方法
             * 中调用的方法
             */
            return postProcess(enhancer.create());
        }
        else{
            return super.postProcessBeforeInstantiation(beanClass,beanName);
        }
    }

    /**
     * 下方enhancer.create()报错，无法使用该方法生成代理，毕竟target都丢失了
     */
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        if(MessageService2.class.isInstance(bean)){
//            Enhancer enhancer = new Enhancer();
//            enhancer.setSuperclass(bean.getClass());
//            enhancer.setInterfaces(new Class[]{SpringProxy.class});
//            enhancer.setCallback((MethodInterceptor)(target, method, args, methodProxy) ->{
//                if(method.getName().endsWith("setMessage")){
//                    System.out.println("执行代理方法");
//                    return methodProxy.invokeSuper(target, args);
//                }
//                return methodProxy.invokeSuper(target, args);
//            });
//            return enhancer.create();
//        }
//        else{
//            return super.postProcessBeforeInstantiation(bean.getClass(),beanName);
//        }
//    }

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
