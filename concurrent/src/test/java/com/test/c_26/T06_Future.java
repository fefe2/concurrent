package com.test.c_26;

import java.util.concurrent.*;

/**
 * Future常与Callable联合使用，Future可以获得Callable执行后的返回值。
 * 如果想新建一个线程执行一个这个Callable中的call方法而且获得返回值的话我们可以使用以下的思路。
 *
 * 方案一：new Thread(new FutureTask(一个实现了Callable的类的对象)).start();使用FutureTask来接收任务的返回值。
 *
 * 方案二：new一个线程池然后然后提交Callable的实现的对象。使用Future来获得Callable的返回值。具体实现如下：
 * 其中它的返回值放在了Future对象中，我们可以使用Future对象的get方法来获得返回值。
 */
 public class T06_Future {
    public static void main(String[] args) {

        FutureTask<Integer> task = new FutureTask<>(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        }); //new Callable () { Integer call();}

        new Thread(task).start();

        try {
            System.out.println(task.get()); //阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //*******************************
        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> f = service.submit(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });
        try {
            System.out.println(f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(f.isDone());//返回 true如果任务已完成。

    }
    }

