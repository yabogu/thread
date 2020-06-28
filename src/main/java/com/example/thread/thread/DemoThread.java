package com.example.thread.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoThread extends Thread{

    @Override
    public void run() {
        log.info("执行线程方法--线程名称:{}",this.getName());
    }

}
