package consumer;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chengmo on 2018/10/29.
 */
@Slf4j
@Component
public class AlphaPersistConsumerTest {
//    @KafkaListener(id = "demo", topics = "deltaQ_GPE_1Q", group = "haizhi")
    @KafkaListener(id = "demo", topics = "mytest1", group = "haizhi")
    public void listen(List<ConsumerRecord<Object, Object>> records, Acknowledgment ack) {
        System.out.println("111111111");
        log.info("records: {}", records.size());
    }

}
