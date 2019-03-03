package com.test.c_22;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 * 这个关键字是使用空间换时间,也就是每一个线程都有自己的不同的变量
 *也就不存在不同步的问题
 *
 * synchronized是使用时间换空间
 * 也就是每个线程都是相同的变量,但是同一时间只能有一个区写变量,来保证同步的
 */
public class ThreadLocal2 {

    volatile static Person p = new Person();
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(tl.get());
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        }).start();
    }

    static class Person {
        String name = "zhangsan";
    }
}
