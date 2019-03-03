package com.test.c_26;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的概念
 *     整个程序new了一个5个线程的线程池，使用for循环向这个线程池抛了5个任务。
 *
 *     它的执行原则是哪一个线程空闲就由哪个线程来执行这个任务。
 *     所以我们看到的线程池的线程序号是不固定的乱序的，
 *     但是它有个规则就是先执行完任务的线程会在新线程到来时优先分配到任务。
 *
 *     线程池shutdown之后程序不会立刻停止而是要等待的所有线程都执行完毕之后再停止服务，
 *     所以我们看到的就是Runningà Shutting downà Terminated
 *
 *     线程池的任务大体上分为两类，等待就绪队列与已完成任务的队列。
 *     通过输出结果我们可以看出在开始有5个正在执行的任务1个任务驻留在就绪队列等待执行，
 *     在执行结束后我们的已执行队列中就会有6个元素。
 */
public class T05_ThreadPool {
    public static void main(String[] args) {
                                             //一个固定大小的线程池
        ExecutorService service = Executors.newFixedThreadPool(5); //execute submit
        for (int i = 0; i < 6; i++) {
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

        service.shutdown();//关闭线程池
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);
    }
}



