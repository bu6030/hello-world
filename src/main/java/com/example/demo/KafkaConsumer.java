package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {
    /**
     * 消费者端：指定监听话题
     * 指定监听的topics消息
     * @param consumerRecord 监听到数据
     */
    @KafkaListener(topics = {"spring_test_kafka_topic"})
    public void handlerMsg(ConsumerRecord<String, String> consumerRecord) {
        log.info("接收到消息：消息值：{} ，消息偏移量：{}，Partition：{}",
                consumerRecord.value(), consumerRecord.offset(), consumerRecord.partition());
    }
}
