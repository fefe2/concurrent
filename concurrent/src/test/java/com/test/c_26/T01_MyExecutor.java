package com.test.c_26;

import java.util.concurrent.Executor;

/**
 *  Executor是java内置的一个线程池接口
 *  里面只有不一个executor方法,用来执行任务的,每执行一次任务都会新建一个线程;
 *  执行器，这是一个接口，内部维护了一个方法execute它负责执行一项任务。
 *  参数为Runnable，方法的具体实现由我们自己来执行。
 *  如下面的代码，我们既可以使用单纯的方法调用也可以新启一个新的线程去执行Runnable的run方法。
 */
public class T01_MyExecutor  implements Executor {
    public static void main(String[] args) {
        new T01_MyExecutor().execute(()->System.out.println("hello executor"));
    }

    @Override
    public void execute(Runnable command) {
        //new Thread(command).run();
        command.run();

    }
}
