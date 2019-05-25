package com.atguigu.test;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.tx.TxConfig;
import com.atguigu.tx.UserService;

public class IOCTest_Tx {

	@Test
	public void test01() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);

		UserService userService = applicationContext.getBean(UserService.class);

		userService.activeTransaction();
		
		userService.insertUser();
		//userService.updateUser();

		applicationContext.close();
	}

	@Test
	public void test() {

		System.out.println(test11());
	}

	public String test11() {
		try {
			System.out.println("try block");

			return test12();
		} finally {
			System.out.println("finally block");
		}
	}

	public static String test12() {
		System.out.println("return statement");

		return "after return";
	}

	@Test
	public void test10() {

		System.out.println(test02());
	}

	public int test02() {
		int b = 20;

		try {
			System.out.println("try block");
			return b+=80;
		} catch (Exception e) {
			System.out.println("catch block");
			//return 110;
		} finally {

			System.out.println("finally block");

			if (b > 25) {
				System.out.println("b>25, b = " + b);
			}
			//b+=1;
			return 200;
		}
	}
	
	@Test
	public void test100() {
		
		
		method1();
	
		
		try {
			method2();
		} catch (CheckException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		method3();
		
		try {
			method4();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			method5();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void method1() throws UnCheckException {
		throw new UnCheckException();
	}
	
	public void method2() throws CheckException {
		throw new CheckException();
	}	
	
	public void method3() {
		throw new RuntimeException();
	}
	
	public void method4() throws Exception {
		throw new Exception();
	}
	
	public void method5() throws IOException {
		throw new IOException();
	}
	
	private class UnCheckException extends RuntimeException{
		
	}
	
	private class CheckException extends Exception{
		
	}
	

}
