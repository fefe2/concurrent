package com.test.c_25;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 一个基于链接节点的无界线程安全队列。此队列按照 FIFO（先进先出）原则对元素进行排序。
 */
public class T04_ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();

        for(int i=0; i<10; i++) {
            strs.offer("a" + i);  //add
        }

        System.out.println(strs);

        System.out.println(strs.size());

        System.out.println(strs.poll());//获取并移除头
        System.out.println(strs.size());

        System.out.println(strs.peek()); //获取但是不移除头
        System.out.println(strs.size());

        //双端队列Deque
    }
}
