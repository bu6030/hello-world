package com.test.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentNewMapper {
    Student getStudent(@Param("id") Long id);

    List<Student> getStudents();

    void saveStudent(@Param("student") Student student);

    void updateStudent(@Param("student") Student student);

    void deleteStudent(@Param("id") String id);
}