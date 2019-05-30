package com.atguigu;

import com.atguigu.aop.MathCalculator;
import com.atguigu.config.MainConfigOfAOP;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;

public class MainTest {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
//		Person bean = (Person) applicationContext.getBean("person");
//		System.out.println(bean);
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
		MathCalculator bean = applicationContext.getBean(MathCalculator.class);
		bean.div(10,2);
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
	
	}

}
