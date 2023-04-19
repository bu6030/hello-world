package com.example.demo;

import com.example.demo.repository.Student;
import com.example.demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Slf4j的注解就是lombok中的注解，日志输出使用
@Slf4j
@Service
//cacheNames与ehcache.xml中的名字相同
@CacheConfig(cacheNames = "student")
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    //注意key，可以配置在cache中的名字，同时可以将参数作为key的一部分存在key中
    @Cacheable(key = "'student_'+#id")
    public Student getStudent(String id) {
        log.info("只有第一次没有缓存时候才调用，第二次就没有日志了，因为请求就不进入方法里了");
        Optional<Student> opt = studentRepository.findById(id);
        //存在返回的Student对象
        if(opt.isPresent()){
            return opt.get();
        }
        //不存在返回null
        return null;
    }
}
