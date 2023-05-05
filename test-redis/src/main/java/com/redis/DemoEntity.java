package com.redis;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class DemoEntity implements Serializable {
    private String id;
    private String name;
    private String gender;
}
