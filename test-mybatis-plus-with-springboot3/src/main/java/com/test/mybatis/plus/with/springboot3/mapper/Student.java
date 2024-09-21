package com.test.mybatis.plus.with.springboot3.mapper;


import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {

    private static final long serialVersionUID = -7748312639957930069L;

    private Long id;
    private String name;

}
