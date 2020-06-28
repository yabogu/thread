package com.example.thread.pool;

import com.example.thread.callable.CallableDemo;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

/***
 * 使用线程池执行线程
 * corePoolSize - 线程池核心池的大小。
 * maximumPoolSize - 线程池的最大线程数。
 * keepAliveTime - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
 * unit - keepAliveTime 的时间单位。
 * workQueue - 用来储存等待执行任务的队列。
 * threadFactory - 线程工厂。
 * handler - 拒绝策略
 *
 * 线程池大小
 * 线程池有两个线程数的设置，一个为核心池线程数，一个为最大线程数。
 * 在创建了线程池后，默认情况下，线程池中并没有任何线程，等到有任务来才创建线程去执行任务，除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法
 * 当创建的线程数等于 corePoolSize 时，会加入设置的阻塞队列。当队列满时，会创建线程执行任务直到线程池中的数量等于maximumPoolSize。
 * 关注点2 适当的阻塞队列
 * java.lang.IllegalStateException: Queue full
 * 方法 抛出异常 返回特殊值 一直阻塞 超时退出
 * 插入方法 add(e) offer(e) put(e) offer(e,time,unit)
 * 移除方法 remove() poll() take() poll(time,unit)
 * 检查方法 element() peek()
 * ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列。
 * LinkedBlockingQueue ：一个由链表结构组成的有界阻塞队列。
 * PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列。
 * DelayQueue： 一个使用优先级队列实现的无界阻塞队列。
 * SynchronousQueue： 一个不存储元素的阻塞队列。
 * LinkedTransferQueue： 一个由链表结构组成的无界阻塞队列。
 * LinkedBlockingDeque： 一个由链表结构组成的双向阻塞队列。
 * 关注点3 明确拒绝策略
 * ThreadPoolExecutor.AbortPolicy: 丢弃任务并抛出
 * RejectedExecutionException异常。 (默认)
 * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
 * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
 * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
 */
@Slf4j
public class PoolTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1,1,10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        Future<Map> mapFuture = threadPool.submit(new CallableDemo(10));
        Map map = mapFuture.get();
        log.info("打印map:{}",map.values());
        //线程池停止1111
        threadPool.shutdown();
    }
}
