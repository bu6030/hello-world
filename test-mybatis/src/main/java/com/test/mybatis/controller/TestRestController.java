package com.test.mybatis.controller;

import com.test.mybatis.mapper.Student;
import com.test.mybatis.mapper.StudentMapper;
import com.test.mybatis.mapper.StudentNewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestRestController {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentNewMapper studentNewMapper;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public Object getStudent(@RequestParam String id){
//        return studentMapper.getStudent(id);
        return studentNewMapper.getStudent(id);
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestParam String id, @RequestParam String name){
        studentMapper.saveStudent(new Student(id,name));
        return "ok";
    }

    @RequestMapping(value = "/student", method = RequestMethod.PUT)
    public String updateStudent(@RequestParam String id, @RequestParam String name){
        studentMapper.updateStudent(id, name);
        return "ok";
    }

    @RequestMapping(value = "/student", method = RequestMethod.DELETE)
    public String deleteStudent(@RequestParam String id){
        studentMapper.deleteStudent(id);
        return "ok";
    }
}
