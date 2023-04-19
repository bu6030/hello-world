package com.example.demo.springboottest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/springboottest")
public class SpringBootTestController {

    @Autowired
    private SpringBootTestService springBootTestService;

    @Autowired
    private SpringBootTestRepository springBootTestRepository;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public Object getStudent(@RequestParam String id){
        Optional<StudentEntity> opt = springBootTestRepository.findById(id);
        //存在返回的Student对象
        if(opt.isPresent()){
            return opt.get();
        }
        //不存在返回null
        return null;
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestParam String id, @RequestParam String name){
        springBootTestRepository.save(new StudentEntity(id,name));
        return "ok";
    }

    @RequestMapping(value = "/student", method = RequestMethod.PUT)
    public String updateStudent(@RequestParam String id, @RequestParam String name){
        springBootTestRepository.save(new StudentEntity(id,name));
        return "ok";
    }

    @RequestMapping(value = "/student", method = RequestMethod.DELETE)
    public String deleteStudent(@RequestParam String id){
        springBootTestRepository.deleteById(id);
        return "ok";
    }

    @PostMapping("/validation")
    public String validation(@Validated @RequestBody ValidationRequest request, BindingResult results) {
        //把实体注解中的错误信息返回
        if (results.hasErrors()) {
            return results.getFieldError().getDefaultMessage();
        }
        return "ok";
    }

    @GetMapping("/getStudentByRestTemplate")
    public String getStudentByRestTemplate(@RequestParam String id) {
        return springBootTestService.getStudentByRestTemplate(id);
    }

    @PostMapping("/addStudentByRestTemplate")
    public String addStudentByRestTemplate(@RequestParam String id, @RequestParam String name) {
       return springBootTestService.addStudentByRestTemplate(id, name);
    }
}
