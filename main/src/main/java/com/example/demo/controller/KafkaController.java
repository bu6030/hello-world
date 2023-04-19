package com.example.demo.controller;//package com.example.demo;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RestController
//public class KafkaController {
//    @Autowired
//    private KafkaProducer kafkaProducer;
//
//    @PostMapping("/sendkafkaMessage")
//    public String sendMQMessage(@RequestParam String content) {
//        log.info("KafkaController content: {}", content);
//        kafkaProducer.sendMessage(content);
//        return "ok";
//    }
//}
