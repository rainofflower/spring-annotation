package com.yanghui.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Scope("prototype")
@Component
public class SendMsgListener implements ApplicationListener<ApplicationEvent>{
	
	public SendMsgListener() {
		System.out.println("Instantiate SendMsgListener...");
	}

	public void onApplicationEvent(ApplicationEvent event) {
		//System.out.println("SendMsgListener..收到消息："+event.getSource());
	}

}
