package com.example.thread.consumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j(topic = "byg.TestConsumer")
public class TestConsumer {

    public static void main(String[] args) throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();
        for(int i=0; i<=3; i++) {
            int id = i;
            new Thread(()->{
                log.info("进入生产者消息");
                messageQueue.putMessage(new Message(id,"生产者消息"+id));
            },"生产者"+i).start();
        }
        Thread.sleep(1000);
        while(true) {
            new Thread(()->{
                Message message = messageQueue.getMessage();
                log.info("id:{},messgae:{}",message.getId(),message.getMessage());
            }).start();
        }

    }

}

@Data
@AllArgsConstructor
class Message {

    private int id;

    private String message;

}

/***
 * 有一个集合放入message
 */
@Slf4j(topic = "byg.MessageQueue")
class MessageQueue {

    private LinkedList<Message> list = new LinkedList();

    private int n = 3;

    public void putMessage(Message m){
        synchronized (list) {
            while (true) {
                if(list.size() >= n) {
                    try {
                        log.info("当前数据已经超出范围执行等待中!");
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //添加到结尾
                list.addLast(m);
                list.notifyAll();
                break;
            }
        }

    }

    public Message getMessage(){
        synchronized (list) {
            while (true) {
                if(list.size() == 0) {
                    try {
                        log.info("当前没有数据执行等待中");
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //取出第一个
                Message message = list.removeFirst();
                list.notifyAll();
                return message;
            }
        }

    }

}
