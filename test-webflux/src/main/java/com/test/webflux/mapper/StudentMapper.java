package com.test.webflux.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface StudentMapper {

    // 对应 XML 中同样的方式，支持一对多，支持一对一
    // 一个学生对应多个书包
    // 一个学生对应一个班主任
    // 如果有几个类似的查询，无法像 XML 中可以抽取出公共的
    // 只能每次 copy 到新方法
    @Results({
            // 注意每一个 Result 配置，property 对应实体的属性值，column 对应 SQL 语句中查询的结果值
            @Result(property = "id",column = "id"),
            @Result(property="name", column="name"),
            @Result(property="studentClass.no", column="class_no"),
            @Result(property="studentClass.name", column="class_name"),
            @Result(property="studentSchool.sno", column="school_no"),
            @Result(property="studentSchool.sname", column="school_name"),
            // 注意下面的 column = "id" 这个是主表 student 的主键 ID，通过这个 id 到 findSchoolBagsByStudentId 方法查询书包数据
            // javaType 配置返回的是列表数据，通过 List 返回
    })
    @Select("        select t.id, " +
            "               t.name, " +
            "               t.class_no, " +
            "               t.class_name, " +
            "               t.school_no, " +
            "               t.school_name " +
            "        from student t " +
            " where t.id = #{id} ")
    Student getStudent(@Param("id") Long id);


    // 对应 XML 中同样的方式，支持一对多，支持一对一
    // 一个学生对应多个书包
    // 一个学生对应一个班主任
    // 如果有几个类似的查询，无法像 XML 中可以抽取出公共的
    // 只能每次 copy 到新方法
    @Results({
            // 注意每一个 Result 配置，property 对应实体的属性值，column 对应 SQL 语句中查询的结果值
            @Result(property = "id",column = "id"),
            @Result(property="name", column="name"),
            @Result(property="studentClass.no", column="class_no"),
            @Result(property="studentClass.name", column="class_name"),
            @Result(property="studentSchool.sno", column="school_no"),
            @Result(property="studentSchool.sname", column="school_name"),
    })
    @Select("        select t.id, " +
            "               t.name, " +
            "               t.class_no, " +
            "               t.class_name, " +
            "               t.school_no, " +
            "               t.school_name " +
            "        from student t ")
    List<Student> getStudents();

    @Results({
            // 注意每一个 Result 配置，property 对应实体的属性值，column 对应 SQL 语句中查询的结果值
            @Result(property = "id",column = "id"),
            @Result(property="name", column="name"),
            @Result(property="studentClass.no", column="class_no"),
            @Result(property="studentClass.name", column="class_name"),
            @Result(property="studentSchool.sno", column="school_no"),
            @Result(property="studentSchool.sname", column="school_name"),
    })
    @Select("        select t.id, " +
            "               t.name, " +
            "               t.class_no, " +
            "               t.class_name, " +
            "               t.school_no, " +
            "               t.school_name " +
            "        from student t order by t.id desc")
    List<Student> getMaxIdStudent();

    // 可以支持实体作为参数传递，使用实体的变量需要通过如下方式使用
    // 新增学生
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
    int saveStudent(@Param("student") Student student);

    // 修改学生
    @Update(" update student " +
            "       set name = #{student.name}," +
            "           class_no= #{student.studentClass.no}," +
            "           class_name= #{student.studentClass.name}," +
            "           school_no= #{student.studentSchool.sno}," +
            "           school_name= #{student.studentSchool.sname}" +
            " where id = #{student.id}")
    void updateStudent(@Param("student") Student student);

    // 删除学生
    @Update("delete from student where id = #{id}")
    void deleteStudent(@Param("id") String id);
}
