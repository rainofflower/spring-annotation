package com.atguigu.handler.extra;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Color {

	public Color(){}

	private Byte red;

	private byte yellow;

	private Integer blue;

	public Color(String s){
		this.color = s;
	}

	@Autowired
	private Car car;

	private String color;

	@Override
	public String toString() {
		return "Color [car=" + car + "]";
	}

}
