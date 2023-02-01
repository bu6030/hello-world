package com.example.demo.rabbitmq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MessageDto implements Serializable {
    @JsonProperty("content")
    private String content;
}
