package com.mongo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageBody {
    private String content;
    private String body;
}
