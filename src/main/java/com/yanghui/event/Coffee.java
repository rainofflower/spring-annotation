package com.yanghui.event;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class Coffee {

	public Coffee() {
		System.out.println("instantiate Coffee...");
	}
}
