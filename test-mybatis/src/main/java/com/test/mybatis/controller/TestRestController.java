package com.test.mybatis.controller;

import com.test.mybatis.mapper.Student;
import com.test.mybatis.mapper.StudentMapper;
import com.test.mybatis.mapper.StudentNewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestRestController {

    // 使用注解方式的 MyBatis
    @Autowired
    private StudentMapper studentMapper;
    // 使用 XML 方式的 MyBatis
    @Autowired
    private StudentNewMapper studentNewMapper;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public Object getStudent(@RequestParam Long id){
//        return studentMapper.getStudent(id);
        return studentNewMapper.getStudent(id);
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public Object getStudents(){
//        return studentMapper.getStudent(id);
        return studentNewMapper.getStudents();
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestBody Student student){
//        studentMapper.saveStudent(student);
        studentNewMapper.saveStudent(student);
        return "ok";
    }

    @RequestMapping(value = "/student", method = RequestMethod.PUT)
    public String updateStudent(@RequestBody Student student){
//        studentMapper.updateStudent(student);
        studentNewMapper.updateStudent(student);
        return "ok";
    }

    @RequestMapping(value = "/student", method = RequestMethod.DELETE)
    public String deleteStudent(@RequestParam String id){
//        studentMapper.deleteStudent(id);
        studentNewMapper.deleteStudent(id);
        return "ok";
    }
}
