package com.atguigu;

import com.atguigu.aop.MathCalculator;
import com.atguigu.bean.*;
import com.atguigu.config.MainConfigOfAOP;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.config.MainConfig;
import org.springframework.util.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MainTest {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
//		Person bean = (Person) applicationContext.getBean("person");
//		System.out.println(bean);

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CircularReference1.class, CircularReference2.class);
		String[] names = applicationContext.getBeanDefinitionNames();
		for (String name: names) {
			System.out.println(name);
		}
		CircularReference1 bean = applicationContext.getBean(CircularReference1.class);
		bean.test();
//		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
//		MathCalculator bean = applicationContext.getBean(MathCalculator.class);
//		bean.div(10,2);
//		String[] names = applicationContext.getBeanDefinitionNames();
//		for (String name: names) {
//			System.out.println(name);
//		}
//		Person bean = applicationContext.getBean(Person.class);
//		System.out.println(bean);
//
//		String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
//		for (String name : namesForType) {
//			System.out.println(name);
//		}

//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
//		context.setConfigLocation("classpath:application-test.xml");
//		context.refresh();
//		MessageService service = context.getBean(MessageService.class);
//		service.setMessage("message1");
//		System.out.println(service.getMessage());
//		System.out.println(service.getYellow());
//		MessageService2 service2 = context.getBean(MessageService2.class);
//		service2.setMessage("message2");
//		System.out.println(service2.getMessage());
//		System.out.println(service2.getYellow());
//		Assert.isInstanceOf(String.class,"4","不是String");
//		System.out.println(Integer.class.isInstance(5));
//		Object color1 = context.getBean("color1");
//		Object color2 = context.getBean("&color2");
//		System.out.println(color2);
//		System.out.println(Color.class.isInstance(color1));

	}


	public void test(String... a){

	}
}
