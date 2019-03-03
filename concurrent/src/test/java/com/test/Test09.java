package com.test;

import java.util.concurrent.TimeUnit;

/**
 * volatile 关键字,使一个变量在多个线程间可见
 * a b  线程都用到一个变量,java默认是a线程中保留一份copy,这样如果b线程改变了该变量,则a线程未必知道
 * 使用了volatile关键字,会让所有的线程都会读到变量的修改值
 * 使用了volatile关键字,将会强制所有的线程都去堆内存中读取变量的值
 * 但是 volatile并不能保证多个线程共同修改变量是所带来的不一致问题,也就是说volatile不能代替synchronized
 */
public class Test09 {
    volatile boolean running = true; //对比一下有无volatile的情况下，整个程序运行结果的区别
    void m() {
        System.out.println("m start");
        while(running) {
			
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {
        Test09 t = new Test09();

        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running = false;


    }

    
}
