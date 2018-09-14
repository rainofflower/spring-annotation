package com.yanghui.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

	@EventListener(classes={ApplicationEvent.class})
	public void sendMsg(ApplicationEvent event){
		System.out.println(event.getSource());
	}
}
