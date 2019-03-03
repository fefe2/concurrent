package com.test.c_26;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CachedThreadPool的主要特点就是如果新来的一个任务需要这个线程池来执行的话，
 * 如果当前线程池没有闲置的线程那么就新启动一个线程，
 * 如果有空闲线程那么就使用其中的一个空闲线程。就是这样的一个有弹性的线程池。
 * 默认情况下当一个线程空闲超过60s那么就会销毁，而且线程数量最大不能超过int类型的最大值或者是计算机内存的大小。
 */
public class T08_CachedPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);
        for (int i = 0; i < 2; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);

        TimeUnit.SECONDS.sleep(80);

        System.out.println(service);


    }
}
