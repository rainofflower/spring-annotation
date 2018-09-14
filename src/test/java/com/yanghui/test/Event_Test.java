package com.yanghui.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yanghui.event.Config_Event;
import com.yanghui.event.MyApplicationEvent;

public class Event_Test {

	@Test
	public void test(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config_Event.class);
		
		applicationContext.publishEvent(new MyApplicationEvent("hi...ÏÂÎç²è"));
		applicationContext.publishEvent(new ApplicationEvent("good night"){});
	}
}
