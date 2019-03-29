package com.geektcp.alpha.hadoop.kafka.consumer;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * Created by tanghaiyang on 2018/10/29.
 */
@Slf4j
@Component
public class AlphaPersistConsumerTest {

//    @KafkaListener(id = "demo1", topics = "deltaQ_GPE_1Q", group = "haizhi")
    @KafkaListener(id = "demo", topics = "mytest1", group = "haizhi")
    public void listen(ConsumerRecord<Object, Object> record, Acknowledgment ack) {
        log.info("partition: {}", record.partition());
        test(record);
    }

    private void test(ConsumerRecord<Object, Object> record){
        log.info("topic: {} | value: {} | timestamp: {}", record.topic(), record.value(), record.timestamp());

    }
}
