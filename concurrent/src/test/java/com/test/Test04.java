package com.test;

/*
非同步方法和同步方法是否可以同时调用
可以
 */
public class Test04 {
    public synchronized void m1() {

        System.out.println(Thread.currentThread().getName() + "m1 start ...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "m1 end ...");
    }

    public void m2() {

        System.out.println(Thread.currentThread().getName() + "m2 start ...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "m2 end ...");
    }


    public static void main(String[] args) {
        Test04 test04 = new Test04();
         //new Thread(test04::m1,"t1").start();
        // new Thread(test04::m2,"t2").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test04.m1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test04.m2();
            }
        }).start();

    }

}
