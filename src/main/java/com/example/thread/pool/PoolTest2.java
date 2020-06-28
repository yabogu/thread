package com.example.thread.pool;

import com.example.thread.callable.CallableDemo;
import com.example.thread.runnable.DemoRunnable;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

/***
 * 使用线程池执行线程
 */
@Slf4j
public class PoolTest2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1,1,10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        DemoRunnable demoRunnable = new DemoRunnable();
        Future<?> future = threadPool.submit(demoRunnable);
        future.get();
        //线程池停止
        threadPool.shutdown();
    }
}
