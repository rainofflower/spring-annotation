package com.yanghui.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.yanghui.event")
public class Config_Event {

	/*@Scope("prototype")
	@Bean
	public Animal animal() {
		return new Animal();
	}*/
}
