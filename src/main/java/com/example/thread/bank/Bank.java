package com.example.thread.bank;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "byg.bank")
/**
 * 使用ReentrantLock
 *
 */
public class Bank {

    private int count =0;//账户余额

    //需要声明这个锁
    private Lock lock = new ReentrantLock();

    //存钱
    public   void addMoney(int money){
        lock.lock();//上锁
        try {
            count +=money;
            log.info("{} 存进：{}",System.currentTimeMillis(),money);
        }finally {
            lock.unlock();//解锁
        }

    }

    //取钱
    public   void subMoney(int money){
        lock.lock();//上锁
        try {
            if(count-money < 0){
                log.info("余额不足");
                return;
            }
            count -=money;
        }finally {
            lock.unlock();//解锁
        }
        log.info("{} 取出：{}",System.currentTimeMillis(),money);
    }

    //查询
    public void lookMoney(){
        log.info("账户余额：{}",count);
    }

}