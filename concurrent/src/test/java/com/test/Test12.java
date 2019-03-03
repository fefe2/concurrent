package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决同样的问题的更高效的方法,使用Atomxxx类
 * Atomxxx类本身方法都是原子性的,但是不能保证多个方法连续调用时原子性的
 */
public class Test12 {
    //volatile int count = 0;//只能保证可见性,不能保证原子性
    AtomicInteger count = new AtomicInteger(0);

    synchronized void m() {
        for (int i = 0; i < 10000; i++)
            if (count.get() < 1000){
                count.incrementAndGet();//count++;
            }

    }

    public static void main(String[] args) {
        Test11 t = new Test11();

        List<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread-" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);

    }


}




