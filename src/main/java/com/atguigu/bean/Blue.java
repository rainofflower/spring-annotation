package com.atguigu.bean;

import org.springframework.stereotype.Component;

@Component
public class Blue {
	
	public Blue(){
		System.out.println("blue...constructor");
	}

	@Calculate
	public void init(){
		System.out.println("blue...init...");
	}
	
	public void detory(){
		System.out.println("blue...detory...");
	}
	
	

}
