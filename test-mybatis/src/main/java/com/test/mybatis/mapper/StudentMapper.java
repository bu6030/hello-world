package com.test.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface StudentMapper {
    @Select("select id,name from student where id = #{id}")
    Student getStudent(@Param("id") String id);

    @Select("select id,name from student")
    List<Student> getStudents();

    // 可以支持实体作为参数传递，使用实体的变量需要通过如下方式使用
    @Update(" insert into student" +
            "            (id," +
            "             name," +
            "             class_no," +
            "             class_name," +
            "             school_no," +
            "             school_name)" +
            "        values" +
            "            (#{student.id}," +
            "             #{student.name}," +
            "             #{student.studentClass.no}," +
            "             #{student.studentClass.name}," +
            "             #{student.studentSchool.sno}," +
            "             #{student.studentSchool.sname})")
    void saveStudent(@Param("student") Student student);

    @Update(" update student " +
            "       set name = #{student.name}," +
            "           class_no= #{student.studentClass.no}," +
            "           class_name= #{student.studentClass.name}," +
            "           school_no= #{student.studentSchool.sno}," +
            "           school_name= #{student.studentSchool.sname}" +
            " where id = #{student.id}")
    void updateStudent(@Param("student") Student student);

    @Update("delete from student where id = #{id}")
    void deleteStudent(@Param("id") String id);
}
