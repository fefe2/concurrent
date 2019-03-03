package com.test.c_26;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPool一个定时执行任务的一个线程池
 * command: 一个可执行任务runnable
 *
 * initialDelay：在开始多少单位时间的时候执行第一个任务。
 *
 * Period：每隔多长时间执行下一个任务。
 *
 * Unit：时间的单位。
 *
 */
public class T10_ScheduledPool {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, 0, 500, TimeUnit.MILLISECONDS);

    }
}
