package com.test;

import java.io.IOException;

/**
 * synchronized关键字
 * 对某个对象加锁
 * @author 陈飞 
 */
public class Test01 implements Runnable {
       private  int count = 10000;
       private  Object o = new Object();



       public  void run (){

           //任何线程要执行下面的代码,都必须要先拿到锁

               count--;

               System.out.println(Thread.currentThread().getName()+" count:"+count);


       }

    public static void main(String[] args) {
        Test01 test01 = new Test01();






    }


}
