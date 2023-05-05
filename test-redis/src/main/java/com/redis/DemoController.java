package com.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public Object getStudent(@RequestParam String id){
        log.info("Request id {}", id);
        //使用SpringBoot Redis注解方式@Cacheable
//        return demoService.getDemoEntity(id);
        //使用SpringBoot RedisTemplate方式（封装成RedisRepository）
        return demoService.getDemoEntityFromRedisTemplate(id);
    }

    @RequestMapping(value = "/demo", method = RequestMethod.POST)
    public String addStudent(@RequestBody DemoEntity demo){
        //使用SpringBoot Redis注解方式@Cacheable
//        demoService.updateDemoEntity(demo.getId(),demo.getName(), demo.getName());
        //使用SpringBoot RedisTemplate方式（封装成RedisRepository）
        demoService.updateDemoEntityFromRedisTemplate(demo.getId(),demo.getName(), demo.getName());
        return "ok";
    }

    @RequestMapping(value = "/demo", method = RequestMethod.PUT)
    public String updateStudent(@RequestBody DemoEntity demo){
        //使用SpringBoot Redis注解方式@CachePut
//        demoService.updateDemoEntity(demo.getId(),demo.getName(), demo.getName());
        //使用SpringBoot RedisTemplate方式（封装成RedisRepository）
        demoService.updateDemoEntityFromRedisTemplate(demo.getId(),demo.getName(), demo.getName());
        return "ok";
    }

    @RequestMapping(value = "/demo", method = RequestMethod.DELETE)
    public String deleteStudent(@RequestParam String id){
        //使用SpringBoot Redis注解方式@CacheEvict
//        demoService.removeDemoEntity(id);
        //使用SpringBoot RedisTemplate方式（封装成RedisRepository）
        demoService.removeDemoEntityFromRedisTemplate(id);
        return "ok";
    }
}
