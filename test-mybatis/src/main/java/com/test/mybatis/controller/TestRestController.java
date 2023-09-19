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

    @RequestMapping(value = "/studentXML", method = RequestMethod.GET)
    public Object getStudentXML(@RequestParam Long id){
        return studentNewMapper.getStudent(id);
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public Object getStudent(@RequestParam Long id){
        return studentMapper.getStudent(id);
    }

    @RequestMapping(value = "/studentsXML", method = RequestMethod.GET)
    public Object getStudentsXML(){
        return studentNewMapper.getStudents();
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public Object getStudents(){
        return studentMapper.getStudents();
    }

    @RequestMapping(value = "/studentXML", method = RequestMethod.POST)
    public String addStudentXML(@RequestBody Student student){
        studentNewMapper.saveStudent(student);
        return "ok";
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestBody Student student){
        studentMapper.saveStudent(student);
        return "ok";
    }

    @RequestMapping(value = "/studentXML", method = RequestMethod.PUT)
    public String updateStudentXML(@RequestBody Student student){
        studentNewMapper.updateStudent(student);
        return "ok";
    }

    @RequestMapping(value = "/student", method = RequestMethod.PUT)
    public String updateStudent(@RequestBody Student student){
        studentMapper.updateStudent(student);
        return "ok";
    }

    @RequestMapping(value = "/studentXML", method = RequestMethod.DELETE)
    public String deleteStudentXML(@RequestParam String id){
        studentNewMapper.deleteStudent(id);
        return "ok";
    }

    @RequestMapping(value = "/student", method = RequestMethod.DELETE)
    public String deleteStudent(@RequestParam String id){
        studentMapper.deleteStudent(id);
        return "ok";
    }
}
