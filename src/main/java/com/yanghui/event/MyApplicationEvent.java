package com.yanghui.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class  MyApplicationEvent extends ApplicationEvent{

	public MyApplicationEvent(Object source) {
		super(source);
	}
}
