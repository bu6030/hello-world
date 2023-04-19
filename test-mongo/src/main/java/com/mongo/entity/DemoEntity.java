package com.mongo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DemoEntity {
    private String id;
    private MessageBody messageBody;
}
