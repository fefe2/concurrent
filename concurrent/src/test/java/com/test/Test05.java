package com.test;

import java.util.concurrent.TimeUnit;

/*
对业务写加锁,对业务读不加锁
会出现脏读问题 (dirtyread)

当写业务只进行到一半还没有完成的时候,就进行读业务,会读到已改变的数据

 */
public class Test05 {
    String name;
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


        this.balance = balance;
    }

    public /*synchronized*/ double getBalance(String name) {
        return this.balance;
    }


    public static void main(String[] args) {
        Test05 a = new Test05();
        new Thread(()->a.set("zhangsan", 100.0)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("zhangsan"));
        

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("zhangsan"));
    }
    

}
