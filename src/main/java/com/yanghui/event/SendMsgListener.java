package com.yanghui.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SendMsgListener implements ApplicationListener{

	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof MyApplicationEvent){
			System.out.println(event.getSource());
		}
	}

}
