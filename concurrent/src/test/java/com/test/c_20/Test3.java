package com.test.c_20;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * * reentrantlock用于替代synchronized
 * * 由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 * * 这里是复习synchronized最原始的语义
 * *
 * * 使用reentrantlock可以完成同样的功能
 * * 需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 * * 使用syn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 * <p>
 * 使用reentranlock可以进行"尝试锁定"trylock,这样无法锁定,或者在指定时间内无法锁定,线程可以决定是否继续等待
 */
public class Test3 {
    Lock lock = new ReentrantLock();

    void m1() {

        try {
            lock.lock();
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

    /**
     * 使用trylock进行尝试锁定,不管锁定与否,方法都将继续执行
     * 可以根据trylock的返回值判定是否锁定
     * 也可以指定trylock的时间,由于trylock(time)抛出异常,所以要注意unlock的处理,必须放到finally中
     */
    void m2() {

       /*
		boolean locked = lock.tryLock();
		System.out.println("m2 ..." + locked);
		if(locked) lock.unlock();
		*/

        boolean locked = false;

        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2 ..." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(locked) lock.unlock();
        }

    }
    public static void main(String[] args) {
        Test3 rl = new Test3();
        new Thread(rl::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(rl::m2).start();
    }
    }



