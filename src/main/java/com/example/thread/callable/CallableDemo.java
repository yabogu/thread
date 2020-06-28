package com.example.thread.callable;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

@Slf4j
public class CallableDemo implements Callable<Map> {

    public CallableDemo() {

    }

    private int n=0;
    public CallableDemo(int n) {
        this.n = n;
    }

    @Override
    public Map call() throws Exception {
        Map map = new HashMap();
        for(int i=0; i<n; i++) {
            log.info("打印i:{}",i);
            map.put(i,i+2);
        }
        return map;
    }

}
