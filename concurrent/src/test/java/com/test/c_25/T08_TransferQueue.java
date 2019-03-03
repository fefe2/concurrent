package com.test.c_25;

import java.util.concurrent.LinkedTransferQueue;

/**
 * TransferQueue：当有消费者的话那么就直接将生产出来的元素交给消费者，
 * 但是如果没有消费者的话就会将生产的元素放到队列中。当队列中的元素消耗完的时候消费者就会阻塞。
 */
public class T08_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

		/*new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/

        //strs.transfer("aaa");

        strs.put("aaa");


        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
