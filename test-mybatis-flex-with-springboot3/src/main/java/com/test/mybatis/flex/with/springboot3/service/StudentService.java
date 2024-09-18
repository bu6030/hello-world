package com.test.mybatis.flex.with.springboot3.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.test.mybatis.flex.with.springboot3.mapper.Student;
import com.test.mybatis.flex.with.springboot3.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public Student getStudent(Long id){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(Student::getId, id);
        Student student = studentMapper.selectOneByQuery(queryWrapper);
        System.out.println(student);
        return student;
    }

    public List<Student> getStudents(){
        QueryWrapper queryWrapper = new QueryWrapper();
        List<Student> students = studentMapper.selectListByQuery(queryWrapper);
        System.out.println(students);
        return students;
    }
}
