package com.yanghui.innerclass;

public class MainTest {

	public static void main(String[] args) {
		/*AbstractA abstractA = new AbstractA() {};
		
		MyInterface1 myInterface1 = new MyInterface1() {

			public void method1() {
				// TODO Auto-generated method stub
				
			}
		};*/
		//�ӿں�ԭ���ϲ�����
		
		B1 b = new B1(new MyInterface1() {	//new�ӿڣ������ڲ���-->ָ��ʵ�����нӿڷ����������

			public void method1() {
					System.out.println("�ӿڻص�");
			}
		});
		
		b.b1();
		
		B1 b1 = new B1(new AbstractB() {	//new�����ࣨ�����ڲ���-->ָ��ʵ�����г��󷽷����������

			void b2() {
				// TODO Auto-generated method stub
				
			}

			
		});
		
		b1.new InnerB1();
	}
}
