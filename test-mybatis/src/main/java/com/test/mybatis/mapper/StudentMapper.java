package com.test.mybatis.mapper;

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
            @Result(property = "id",column = "id"),
            @Result(property="name", column="name"),
            @Result(property="studentClass.no", column="class_no"),
            @Result(property="studentClass.name", column="class_name"),
            @Result(property="studentSchool.sno", column="school_no"),
            @Result(property="studentSchool.sname", column="school_name"),
            @Result(property = "schoolBags",column = "id", javaType = List.class,
                    many = @Many(select = "com.test.mybatis.mapper.StudentMapper.findSchoolBagsByStudentId")),
            @Result(property = "teacher",column = "id", javaType = com.test.mybatis.mapper.Teacher.class,
                    one = @One(select = "com.test.mybatis.mapper.StudentMapper.findTeacherByStudentId"))
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

    // 一个学生对应多个书包的查询结果集 SQL 以及 Result 配置
    @Results({
            @Result(property = "id", column = "school_bag_id"),
            @Result(property = "name", column = "shool_bag_name")
    })
    @Select("select s.id AS school_bag_id, s.name AS shool_bag_name from school_bag s where s.student_id = #{studentId} ")
    List<SchoolBag> findSchoolBagsByStudentId(@Param("studentId") Long studentId);

    // 一个学生对应一个班主任的查询结果集 SQL 以及 Result 配置
    @Results({
            @Result(property = "id", column = "teacher_id"),
            @Result(property = "name", column = "teacher_name")
    })
    @Select("select s.id AS teacher_id, s.name AS teacher_name from teacher s where s.student_id = #{studentId} ")
    Teacher findTeacherByStudentId(@Param("studentId") Long studentId);

    // 查询所有学生列表
    // 对应 XML 中同样的方式，支持一对多，支持一对一
    // 一个学生对应多个书包
    // 一个学生对应一个班主任
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property="name", column="name"),
            @Result(property="studentClass.no", column="class_no"),
            @Result(property="studentClass.name", column="class_name"),
            @Result(property="studentSchool.sno", column="school_no"),
            @Result(property="studentSchool.sname", column="school_name"),
            @Result(property = "schoolBags",column = "id", javaType = List.class,
                    many = @Many(select = "com.test.mybatis.mapper.StudentMapper.findSchoolBagsByStudentId")),
            @Result(property = "teacher",column = "id", javaType = com.test.mybatis.mapper.Teacher.class,
                    one = @One(select = "com.test.mybatis.mapper.StudentMapper.findTeacherByStudentId"))
    })
    @Select("        select t.id, " +
            "               t.name, " +
            "               t.class_no, " +
            "               t.class_name, " +
            "               t.school_no, " +
            "               t.school_name " +
            "        from student t ")
    List<Student> getStudents();

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
    void saveStudent(@Param("student") Student student);

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
