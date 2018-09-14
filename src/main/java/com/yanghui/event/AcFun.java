package com.yanghui.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

//@DependsOn("coffee")
@Component
public class AcFun{

	/*@Autowired
	private Money money;*/
	
	//@Autowired
	private Coffee coffee;
	
	@Autowired
	private Friend friend;
	
	public AcFun() {
		System.out.println("instantiate AcFun...");
	}

	public Coffee getCoffee() {
		return coffee;
	}

	public void setCoffee(Coffee coffee) {
		this.coffee = coffee;
	}

	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}
}
