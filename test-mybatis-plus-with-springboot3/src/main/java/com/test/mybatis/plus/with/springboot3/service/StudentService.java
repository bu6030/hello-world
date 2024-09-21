package com.test.mybatis.plus.with.springboot3.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.mybatis.plus.with.springboot3.mapper.Student;
import com.test.mybatis.plus.with.springboot3.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public Student getStudent(Long id){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        Student student = studentMapper.selectOne(queryWrapper);
        System.out.println(student);
        return student;
    }

    public List<Student> getStudents(){
        QueryWrapper queryWrapper = new QueryWrapper();
        List<Student> students = studentMapper.selectList(queryWrapper);
        System.out.println(students);
        return students;
    }

    public int updateStudentById(Long id, String name) {
        Student student = new Student();
        student.setName(name);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        return studentMapper.update(student, queryWrapper);
    }
}
