package com.example.thread.thread;

public class DemoThreadTest {

    public static void main(String[] args) {
        DemoThread demoThread1= new DemoThread();
        demoThread1.start();
        DemoThread demoThread2 = new DemoThread();
        demoThread2.start();
    }
}
