package com.example.thread.callable;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.FutureTask;

@Slf4j
public class CallableTest {

    public static void main(String[] args) throws Exception{
        CallableDemo callableDemo = new CallableDemo(10);
        FutureTask<Map> ft = new FutureTask<>(callableDemo);
        //启动线程
        new Thread(ft).start();
        //获取返回值
        Map map = ft.get();
        log.info("打印map:{}",map.values());
    }

}
