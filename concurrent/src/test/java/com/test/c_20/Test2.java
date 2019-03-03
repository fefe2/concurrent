package com.test.c_20;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * * reentrantlock用于替代synchronized
 * * 由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 * * 这里是复习synchronized最原始的语义
 * <p>
 * 使用reentrantlock可以完成同样的功能
 * 需要注意的是,必须必须必须要手动释放锁
 * 使用syn锁定的话遇到异常,jvm会自动释放锁,但是lock必须手动释放锁,因此经常在finally中进行锁的释放
 */
public class Test2 {
    ReentrantLock lock = new ReentrantLock();

    void m1(){

        try {
            lock.lock();//相当于synchonized(this)
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);

                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    void m2() {
        lock.lock();
        System.out.println("m2 ...");
        lock.unlock();
    }

    public static void main(String[] args) {


        Test2 rl = new Test2();
        new Thread(rl::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(rl::m2).start();
    }



}
