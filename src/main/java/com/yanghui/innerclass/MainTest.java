package com.yanghui.innerclass;

public class MainTest {

	public static void main(String[] args) {
		/*AbstractA abstractA = new AbstractA() {};
		
		MyInterface1 myInterface1 = new MyInterface1() {

			public void method1() {
				// TODO Auto-generated method stub
				
			}
		};*/
		
		//接口和抽象类不可以直接new,new匿名内部类并不是new 接口/抽象类，而是new 接口实现类/抽象类的子类
		B1 b = new B1(new MyInterface1() {	//new接口（匿名内部类-->指向实现所有接口方法的类对象）

			public void method1() {
					System.out.println("接口回调");
			}
		});
		
		b.b1();
		
		B1 b1 = new B1(new AbstractB() {	//new抽象类（匿名内部类-->指向实现所有抽象方法的子类对象）

			void b2() {
				// TODO Auto-generated method stub
				
			}

			
		});
		
		b1.new InnerB1();
	}
}
