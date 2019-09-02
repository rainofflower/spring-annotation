package com.yanghui.test;

import com.atguigu.bean.Color;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectTest {

    /**
     * spring实例化非工厂、无工厂方法、无需代理（包括无方法重写、无切点、无自定义InstantiationAwareBeanPostProcessor返回bean）的单例bean时会获取声明的无参构造方法
     * （调用clazz.getDeclaredConstructor((Class[]) null)）
     *  然后 调用constructor.setAccessible(true);
     *  再执行constructor.newInstance(args);
     *
     *  注意：clazz.getDeclaredConstructor(args)如果找不到匹配的方法，会抛出NoSuchMethodException,
     *  所以对于上面说到spring实例化bean的类,必须保证其包含一个无参构造方法，未声明任何构造方法默认会有一个无参构造方法，
     *  但是对于声明了有参构造方法的类，必须再声明一个无参构造方法，因为一旦类里面有了有参构造方法，java就不会再给一个默认的无参构造方法
     */
    @Test
    public void test1() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //找声明的无参构造方法，调用构造器的newInstance()方法
//        Color color = Color.class.newInstance();
//		System.out.println(Color.class.isInstance(color));
        Class<?> colorClazz = Class.forName("com.atguigu.bean.Color");
        //获取public构造方法，含继承的方法
//		Constructor<?> constructor = colorClazz.getConstructor();
        //获取声明的构造方法，不含继承的方法
        Constructor<?> constructor1 = colorClazz.getDeclaredConstructor((Class[]) null);
        //构造方法调用newInstance方法会检查是否有访问权，调用setAccessible可直接修改访问权
//		constructor1.setAccessible(true);
        Object color6 = constructor1.newInstance();
//		Object color4 = constructor.newInstance();
//		System.out.println(Color.class.isInstance(color4));

//        Object color5 = colorClazz.newInstance();
//        System.out.println(Color.class.isInstance(color5));
        //调用clazz.newInstance()类似于执行new无参构造方法
        Color color = new Color();
        byte yellow = color.getYellow();
    }
}
