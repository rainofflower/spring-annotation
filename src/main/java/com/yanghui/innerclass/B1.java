package com.yanghui.innerclass;

public class B1 extends AbstractA{
	
	MyInterface1 myInterface1; //持有接口
	
	public B1() {
		// TODO Auto-generated constructor stub
	}
	
	public B1(MyInterface1 myInterface1) {
		this.myInterface1 = myInterface1;
	}
	
	public B1(AbstractB abstractB) {
		// TODO Auto-generated constructor stub
	}
	
	public B1(InnerB1 innerB1) {
		// TODO Auto-generated constructor stub
	}


	public void b1() {
		System.out.println("b1");
		//innerB1();
		InnerB1 innerB1 = new InnerB1();
		innerB1.innerB1();
		innerB1.b();												//调用内部类继承方法
		this.a();													//调用外部类继承方法
		//innerB1.method2();
		myInterface1.method1();										//接口回调
	}
	
	class InnerB1 extends AbstractB implements MyInterface2 {		//内部类实现伪双重继承（多重继承）
		
		public void innerB1() {
			System.out.println("innerB1");
			//b1();
		}

		public void method2() {
			// TODO Auto-generated method stub
			
		}

		void b2() {
			// TODO Auto-generated method stub
			
		}

	}
	
	/*class InnerB2 extends AbstractB{
		
	}*/

}
