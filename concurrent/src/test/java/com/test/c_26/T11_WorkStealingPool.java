package com.test.c_26;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * WorkStealingPool工作窃取线程池，一般情况下CPU是几核的就会启动几个线程，
 * 每一个线程都维护者自己的一个执行队列的，
 * 当某些线程将自己队列中的任务都执行完毕的时候就会去其他线程的队列中窃取任务来执行以此提高效率。
 * 它的底层是基于ForkJoinPool的，常常用于任务分配不均匀的场景中
 * 这个线程池产生的都是daemon的线程（后台线程）
 */
public class T11_WorkStealingPool {
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000)); //daemon
        service.execute(new R(2000));

        //由于产生的是精灵线程（守护线程、后台线程），主线程不阻塞的话，看不到输出
        System.in.read();
    }

    static class R implements Runnable {

        int time;

        R(int t) {
            this.time = t;
        }

        @Override
        public void run() {

            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(time  + " " + Thread.currentThread().getName());

        }

    }
}
