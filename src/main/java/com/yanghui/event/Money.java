package com.yanghui.event;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class Money {
	
	public Money() {
		System.out.println("instantiate Money...");
	}

}
