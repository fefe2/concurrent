package com.test.c_25;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class LinkedTransferQueue { //容量为0,直接将产品交给消费者
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa"); //阻塞等待消费者消费
        //strs.add("aaa");
        System.out.println(strs.size());
    }
}
