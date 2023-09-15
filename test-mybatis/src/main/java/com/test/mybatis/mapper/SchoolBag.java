package com.test.mybatis.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolBag {
    private static final long serialVersionUID = -7699349163224691756L;
    private String id;
    private String name;
}