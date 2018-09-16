package com.yanghui.test;

import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yanghui.event.AcFun;
import com.yanghui.event.Coffee;
import com.yanghui.event.Config_Event;
import com.yanghui.event.MyApplicationEvent;

public class Event_Test {

	@Test
	public void test(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config_Event.class);
		
		applicationContext.publishEvent(new MyApplicationEvent("hi...下午茶"));
		applicationContext.publishEvent(new ApplicationEvent("good night"){});
	}
	
	@Test
	public void test2(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config_Event.class);
		
		/*AcFun acfun1 = (AcFun) applicationContext.getBean("acFun");
		Coffee coffee1 = (Coffee) applicationContext.getBean("coffee");
		Coffee coffee2 = (Coffee) applicationContext.getBean("coffee");
		System.out.println(acfun1.getCoffee() == coffee1);
		System.out.println(coffee1 == coffee2);
		Map<String,Coffee> map = applicationContext.getBeansOfType(Coffee.class);
		System.out.println(map.get("coffee") == coffee1);*/
		applicationContext.publishEvent(new ApplicationEvent("说走咋就走"){});
		applicationContext.getBean("msgService");
		applicationContext.publishEvent(new ApplicationEvent("说走咋就走啊"){});

	}
}
