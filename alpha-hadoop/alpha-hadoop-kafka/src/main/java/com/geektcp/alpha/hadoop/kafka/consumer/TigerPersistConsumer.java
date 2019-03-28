package com.geektcp.alpha.hadoop.kafka.consumer;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chengmo on 2018/10/29.
 */
@Component
public class TigerPersistConsumer {


    @KafkaListener(topicPattern = "*", containerFactory = "batchFactory")
    public void listen(List<ConsumerRecord<Object, Object>> records, Acknowledgment ack) {
        System.out.println("111111111");
    }

}
