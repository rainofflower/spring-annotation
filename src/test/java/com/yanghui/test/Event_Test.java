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
		
		applicationContext.publishEvent(new MyApplicationEvent("hi...�����"));
		applicationContext.publishEvent(new ApplicationEvent("good night"){});
	}
	
	/**
	 * �¼�ԭ��Դ�������
	 * 
	 * 1��Spring�¼�ע��@EventListenerʵ�ּ���ԭ���������е�ʵ��bean�������֮��
	 * ��11��ĩβͨ��EventListenerMethodProcessor������ӵ�Spring�������¼��ɷ����еģ�
	 * ���ʹ��@scope("prototype")�Ķ�ʵ��bean��λ�ȡҲ�����ٴ����listener���ɷ�������
	 * ���ٴλ�ȡbean��springֻ����createBean��populateBean,initializeBean���̣��м����beanPostProcesserҲ�͵�ʵ������һ����ִ�У���
	 * ��ʼ��֮�󼴷��ض��󣬶������ٵ�����SmartInitializingSingleton��EventListenerMethodProcessorʵ�ֵĽӿڣ����̣���
	 * ����޷�Ϊÿ��ʵ����Ӽ���
	 * 
	 *	2��ʵ��ApplicationListener�ӿڵļ�����������Spring������ʼ���ĵ�10��-->registerListenersʱ
	 *	����getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);��ӵ��ɷ����е�
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
		applicationContext.publishEvent(new ApplicationEvent("˵��զ����"){});
		//applicationContext.getBean(MsgService.class);
		applicationContext.getBean(Animal.class);
		applicationContext.publishEvent(new ApplicationEvent("˵��զ���߰�"){});

		
		applicationContext.close();
	}
}
