package com.atguigu.bean;

/**
 * @author yanghui
 * @date 2019-8-13
 */
public class ContextHolder {

    private static final ThreadLocal<Integer> CURRENT = new ThreadLocal<>();

    public static Integer get(){
        Integer i = CURRENT.get();
        System.out.println("获取到"+Thread.currentThread().getName()+"线程 threadLocal中值为："+i);
        return i;
    }

    public static void set(Integer v){
        System.out.println("设置"+Thread.currentThread().getName()+"线程 threadLocal中的值为："+v);
        CURRENT.set(v);
    }
}
