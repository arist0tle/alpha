package com.haizhi.queue;

/* Created by Haiyang on 2018/3/5. */


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BlockingQueueTest2 {
    public static void main(String[] args) {
        try {
            LinkedBlockingQueue<String> bq = new LinkedBlockingQueue<String>();
            log.info("bq.remainingCapacity():{}",bq.remainingCapacity());
            // ------ add offer put类似，都会增加队列长度1个元素
            // 在尾部如果队列已满，则抛出异常
            bq.add("add1");
            bq.add("add2");
            bq.add("add3");
            bq.add("add4");

            // 添加一个元素并返回true  如果队列已满，则返回false。
            bq.offer("off1");
            bq.offer("off2", 5, TimeUnit.SECONDS);

            // 添加一个元素  如果队列满则阻塞。
            bq.put("put1");
            bq.put("put2");
            log.info("size:{}", bq.size());

            // ------ take poll remove类似，都会减掉队列长度1个元素
            // 移除并返回队列头部的元素  如果队列为空则阻塞
            String ret_take = bq.take(); // i0
            log.info("size:{}, ret_take: {}", bq.size(), ret_take);
            log.info("size: {}, element: {}", bq.size(),bq.take());
            log.info("size: {}, element: {}", bq.size(),bq.take());

            // 移除并返回队列头部的元素  如果队列为空则返回null，如果不能立即取出，可以设置等待时间
            String ret_poll = bq.poll();
            bq.poll(10,TimeUnit.SECONDS);
            log.info("size:{}, ret_poll: {}", bq.size(), ret_poll );

            // 移除并返回队列头部的元素，如果队列已空，则抛出NoSuchElementException异常
            String ret_remove = bq.remove();
            log.info("size:{}, ret_remove: {}", bq.size(), ret_remove );

            // ------ peek element类似，都不改变队列长度
            // 返回队列头部的元素  如果队列为空则返回null
            String ret_peek = bq.peek();
            log.info("size:{}, ret_peek: {}", bq.size(), ret_peek);

            // 返回队列头部的元素，如果队列为空，则抛出NoSuchElementException异常
            String ret_element = bq.element();
            log.info("size:{}, ret_element: {}", bq.size(), ret_element );

            // 遍历队列
            for(int i=0; i<bq.size();i++){
                log.info("bq.take:{}", bq.take());
            }

            log.info("bq.remainingCapacity():{}",bq.remainingCapacity());

            // 从队列彻底移除所有元素
            bq.clear();
            log.info("size:{}", bq.size());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
