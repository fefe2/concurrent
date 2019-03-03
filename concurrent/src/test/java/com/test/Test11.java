package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
 //没有出现相应的结果
public class Test11 {

    volatile int count = 0;//只能保证可见性,不能保证原子性

    synchronized void m() {
        for (int i = 0; i < 100000; i++)
            count++;
    }

    public static void main(String[] args) {
        Test11 t = new  Test11();

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
