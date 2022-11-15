package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageProducer {

    private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private RabbitTemplate pointRabbitTemplate;

    /**
     * 发送消息
     * @param content
     */
    public void sendMessageToMQ(String content) {
        MessageDto dto = new MessageDto();
        dto.setContent(content);
        pointRabbitTemplate.convertAndSend(this.format(dto));
    }

    public static String format(Object pojo) {
        try {
            return mapper.writeValueAsString(pojo);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException, pojo = {}", pojo, e);
            return "{}";
        }
    }
}
