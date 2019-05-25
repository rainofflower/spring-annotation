package com.yanghui.event;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class Animal implements ApplicationContextAware{
	
	private GenericApplicationContext applicationContext;
	
	private ApplicationListener<ApplicationEvent>  listener;
	
	public Animal() {
		System.out.println("Instantiate Animal...");
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = (GenericApplicationContext)applicationContext;
		this.listener = new ApplicationListener<ApplicationEvent>() {
			public void onApplicationEvent(ApplicationEvent event) {
				System.out.println("=====>"+event);				
			}
		};
		this.applicationContext.addApplicationListener(listener);
	}

	//多例bean时该注解无效
	@PreDestroy
	public void destroy() throws Exception {
		//this.applicationContext.
		System.out.println("销毁");
	}	
	
}
