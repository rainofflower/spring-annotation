package com.atguigu.handler;

import com.atguigu.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableHandlerPipeline
@ComponentScan("com.atguigu.handler")
public class AppConfig {

	@Bean("person")
	public Person person01(){
		return new Person("lisi", 20);
	}

}
