package com.test.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface StudentNewMapper {
    Student getStudent(@Param("id") String id);
//
//    void saveStudent(@Param("student") Student student);
//
//    void updateStudent(@Param("id") String id, @Param("name") String name);
//
//    void deleteStudent(@Param("id") String id);
}