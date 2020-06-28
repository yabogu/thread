package com.example.thread.runnable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoRunnableTest {

    public static void main(String[] args) {
        DemoRunnable demoRunnable = new DemoRunnable();
        Thread t1 = new Thread(demoRunnable);
        log.info(t1.getName());
        t1.start();
        Thread t2 = new Thread(demoRunnable);
        log.info(t2.getName());
        t2.start();
    }
}
