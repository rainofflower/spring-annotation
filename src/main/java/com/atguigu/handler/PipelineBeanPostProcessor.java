package com.atguigu.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author yanghui
 * @date 2020-08-13 17:42
 **/
@Slf4j
public class PipelineBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        if(clazz.isAnnotationPresent(Pipeline.class)){
            Map<String, Object> handlerMap = applicationContext.getBeansWithAnnotation(Handler.class);
            if(CollectionUtils.isEmpty(handlerMap)){
                log.warn("pipeline[{}] 找不到 handler",clazz);
            }else{
                Map<Object,String> handlerNameMap = new HashMap<>();
                for(Map.Entry<String, Object> entry : handlerMap.entrySet()){
                    String name = entry.getKey();
                    Object handler = entry.getValue();
                    handlerNameMap.put(handler,name);
                }
                Collection<Object> handlers = handlerNameMap.keySet();
                Iterator<Object> iterator = handlers.iterator();
                Map<Object,Handler.BindPipelineInfo> bindMap = new HashMap<>();
                while(iterator.hasNext()){
                    Object obj = iterator.next();
                    List<Handler.BindPipelineInfo> bindPipelineInfos = Arrays.asList(obj.getClass().getAnnotation(Handler.class).value());
                    if(CollectionUtils.isEmpty(bindPipelineInfos)){
                        iterator.remove();
                    }else{
                        boolean exist = false;
                        for(Handler.BindPipelineInfo bindInfo : bindPipelineInfos){
                            Class<? extends CommandHandlerPipeline> pClass = bindInfo.pipeline();
                            if(pClass == clazz){
                                bindMap.put(obj,bindInfo);
                                exist = true;
                                break;
                            }
                        }
                        if(!exist){
                            iterator.remove();
                        }
                    }
                }
                if(CollectionUtils.isEmpty(handlers)){
                    log.warn("pipeline[{}] 找不到 handler",clazz);
                }else{
                    handlers = handlers.stream().sorted((o1, o2) -> {
                        int order1 = bindMap.get(o1).order();
                        int order2 = bindMap.get(o2).order();
                        return order1 - order2;
                    }).collect(Collectors.toList());
                    CommandHandlerPipeline pipeline = (CommandHandlerPipeline)bean;
                    System.out.println("---"+beanName+"---");
                    for(Object obj : handlers){
                        CommandHandler handler = (CommandHandler)obj;
                        pipeline.addLast(handlerNameMap.get(handler),handler);
                    }
                }
            }
        }
        return bean;
    }
}
