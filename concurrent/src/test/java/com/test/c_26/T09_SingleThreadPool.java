package com.test.c_26;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 这个线程池中只有一个线程，那么你可能回会问这与单个线程有什么区别呢？: - )
 *
 * 原因就是它可以被复用！它的使用场景就是当我们需要保证任务执行的先后顺序的时候就可以使用它。
 */
public class T09_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for(int i=0; i<5; i++) {
            final int j = i;
            service.execute(()->{

                System.out.println(j + " " + Thread.currentThread().getName());
            });
        }

    }
}
