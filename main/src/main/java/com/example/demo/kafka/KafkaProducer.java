package com.example.demo.kafka;//package com.example.demo;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class KafkaProducer {
//    @Autowired
//    private KafkaTemplate<String,String> kafkaTemplate;
//
//    public String sendMessage(String content) {
//        // 发送消息到kafka
//        // 需要使用KafkaTemplate
//        // 可以指定partition为0（我的默认就一个，所以是0，如果kafka服务器支持多个可以指定其他数字）
//        String topic = "spring_test_kafka_topic";
//        kafkaTemplate.send(topic, 0, "spring_test_kafka_topic_key", content);
//        kafkaTemplate.send(topic, 0, "spring_test_kafka_topic_key", content);
//        return "发送成功";
//    }
//}
