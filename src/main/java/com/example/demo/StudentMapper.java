package com.example.demo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface StudentMapper {
    @Select("select id,name from student where id = #{id}")
    Student getStudent(@Param("id") String id);

    // 可以支持实体作为参数传递，使用实体的变量需要通过如下方式使用
    @Update("insert into student (id, name) values (#{student.id},#{student.name})")
    void saveStudent(@Param("student") Student student);

    @Update("update student set name = #{name} where id = #{id}")
    void updateStudent(@Param("id") String id, @Param("name") String name);

    @Update("delete from student where id = #{id}")
    void deleteStudent(@Param("id") String id);
}
