package com.example.thread.runnable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoRunnable implements Runnable{

    @Override
    public void run() {
        log.info("Runnable执行线程方法");
    }

}
