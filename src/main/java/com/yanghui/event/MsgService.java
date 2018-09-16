package com.yanghui.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Scope("prototype")
@Service
public class MsgService {

	public MsgService() {
		System.out.println("Instantiate MsgService...");
	}
	
	@EventListener(ApplicationEvent.class)
	public void handle(ApplicationEvent event){
		System.out.println("Ūɶ�ϣ�����"+event);
	}
}
