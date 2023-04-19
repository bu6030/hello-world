package com.example.demo.controller;//package com.example.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class RabbitMQController {
//    @Autowired
//    private MessageProducer messageProducer;
//
//    @PostMapping("/sendMQMessage")
//    public String sendMQMessage(@RequestParam String content) {
//        messageProducer.sendMessageToMQ(content);
//        return "ok";
//    }
//}
