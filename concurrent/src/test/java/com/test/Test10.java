package com.test;

import java.util.ArrayList;

/**
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题,也就是说volatile1不能替代synchronized
 * 
 */
public class Test10 {
  volatile int count = 0;
  void m(){

      for (int i = 0; i < 10000; i++) {
          count++;
      }
  }

    public static void main(String[] args) {

        Test10 test10 = new Test10();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(test10::m,"thread"+i));
            
        }

        for (Thread thread : threads) {
            thread.start();
        }
        
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(test10.count);

    }
    
}
