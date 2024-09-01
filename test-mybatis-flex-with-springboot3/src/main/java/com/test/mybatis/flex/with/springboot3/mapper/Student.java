package com.test.mybatis.flex.with.springboot3.mapper;


import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Table("student")
public class Student implements Serializable {

    private static final long serialVersionUID = -7748312639957930069L;

    @Id(keyType = KeyType.Auto)
    private String id;
    private String name;

}
