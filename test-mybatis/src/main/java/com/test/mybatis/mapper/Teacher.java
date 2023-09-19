package com.test.mybatis.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private static final long serialVersionUID = 4976235843125918808L;
    private String id;
    private String name;
}