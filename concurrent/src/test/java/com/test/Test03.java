package com.test;

public class Test03 implements Runnable {

   //加关键字的run方法
   private int count = 10;
    //当没有synchronize关键字的时候,资源共享会出现线程不安全,当一个线程对count进行了操作的时候,还没有来得及输出,别的线程又对count
    //进行了操作,输出的count就会出现问题
    //当加了关键字synchronize的时候,只要对方法进行操作,就会锁住对象this,别的线程进不来,就不会出现问题;
    public  synchronized void run(){
        count--;
        System.out.println(Thread.currentThread().getName()+"count:"+count);


    }

    public static void main(String[] args) {

        Test02 test02 = new Test02();

        for (int i = 0; i < 10; i++) {

            new Thread(test02,"THREAD"+i).start();

        }
    }
}
