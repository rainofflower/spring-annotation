package com.yanghui.innerclass;

public class B1 extends AbstractA{
	
	MyInterface1 myInterface1; //���нӿ�
	
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
		innerB1.b();												//�����ڲ���̳з���
		this.a();													//�����ⲿ��̳з���
		//innerB1.method2();
		myInterface1.method1();										//�ӿڻص�
	}
	
	class InnerB1 extends AbstractB implements MyInterface2 {		//�ڲ���ʵ��α˫�ؼ̳У����ؼ̳У�
		
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
