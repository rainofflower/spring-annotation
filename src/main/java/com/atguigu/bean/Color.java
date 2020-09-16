package com.atguigu.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class Color {

	public Color(){}

	private Byte red = (byte)127;

	private byte yellow;

	private Integer blue;

	public Color(String s){
		this.color = s;
	}

	@Autowired(required = false)
	private Car car;

	private String color;

	@Override
	public String toString() {
		return "Color [car=" + car + "]";
	}

}
