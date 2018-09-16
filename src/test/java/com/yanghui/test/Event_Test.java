package com.yanghui.test;

import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yanghui.event.AcFun;
import com.yanghui.event.Animal;
import com.yanghui.event.Coffee;
import com.yanghui.event.Config_Event;
import com.yanghui.event.MsgService;
import com.yanghui.event.MyApplicationEvent;
import com.yanghui.event.SendMsgListener;

public class Event_Test {

	@Test
	public void test(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config_Event.class);
		
		applicationContext.publishEvent(new MyApplicationEvent("hi...下午茶"));
		applicationContext.publishEvent(new ApplicationEvent("good night"){});
	}
	
	/**
	 * 事件原理源码解析二
	 * 
	 * 1、Spring事件注解@EventListener实现监听原理是在所有单实例bean创建完成之后
	 * （11步末尾通过EventListenerMethodProcessor处理添加到Spring容器的事件派发器中的）
	 * 因此使用@scope("prototype")的多实例bean多次获取也不会再次添加listener到派发器中了
	 * （再次获取bean，spring只会走createBean，populateBean,initializeBean流程（中间各种beanPostProcesser也和单实例对象一样会执行），
	 * 初始化之后即返回对象，而不会再到遍历SmartInitializingSingleton（EventListenerMethodProcessor实现的接口）流程），
	 * 因此无法为每个实例添加监听
	 * 
	 *	2、实现ApplicationListener接口的监听器都是在Spring容器初始化的第10步-->registerListeners时
	 *	调用getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);添加到派发器中的
	 */
	@Test
	public void test2(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config_Event.class);
		
		/*AcFun acfun1 = (AcFun) applicationContext.getBean("acFun");
		Coffee coffee1 = (Coffee) applicationContext.getBean("coffee");
		Coffee coffee2 = (Coffee) applicationContext.getBean("coffee");
		System.out.println(acfun1.getCoffee() == coffee1);
		System.out.println(coffee1 == coffee2);
		Map<String,Coffee> map = applicationContext.getBeansOfType(Coffee.class);
		System.out.println(map.get("coffee") == coffee1);*/
		applicationContext.getBean(Animal.class);
		applicationContext.publishEvent(new ApplicationEvent("说走咋就走"){});
		//applicationContext.getBean(MsgService.class);
		applicationContext.getBean(Animal.class);
		applicationContext.publishEvent(new ApplicationEvent("说走咋就走啊"){});

		
		applicationContext.close();
	}
}
