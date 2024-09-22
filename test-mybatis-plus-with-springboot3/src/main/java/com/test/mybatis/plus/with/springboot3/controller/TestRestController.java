package com.test.mybatis.plus.with.springboot3.controller;

import com.test.mybatis.plus.with.springboot3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public Object getStudent(@RequestParam Long id){
        return studentService.getStudent(id);
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public Object getStudents() {
        return studentService.getStudents();
    }

    @RequestMapping(value = "/student", method = RequestMethod.PUT)
    public int updateStudent(@RequestParam Long id, @RequestParam String name) {
        return studentService.updateStudentById(id, name);
    }

    @RequestMapping(value = "/student/lambda", method = RequestMethod.PUT)
    public int lambdaUpdateStudent(@RequestParam Long id, @RequestParam String name) {
        return studentService.lambdaUpdateStudentById(id, name);
    }
}
