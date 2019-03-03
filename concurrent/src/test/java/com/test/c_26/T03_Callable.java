package com.test.c_26;

import java.util.concurrent.*;

/**
 * 认识Callable，对Runnable进行了扩展也是一个接口,
 * 只有一个call方法(相当于runnable中的run方法,只是有了返回值)
 * 对Callable的调用，可以有返回值
 * 其中它的返回值放在了Future对象中，我们可以使用Future对象的get方法来获得返回值。
 */


public class T03_Callable {

    FutureTask<Integer> task = new FutureTask<>(()->{
        //task对象就是返回值
        TimeUnit.MILLISECONDS.sleep(500);
        return 1000;
    });
    ExecutorService service = Executors.newFixedThreadPool(5);
    Future<Integer> f = service.submit(()->{
        TimeUnit.MILLISECONDS.sleep(500);
        return 1;
    });
}
