package com.mongo;

import com.mongo.entity.DemoEntity;
import com.mongo.repository.DemoEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class DemoController {

    // 类似与JPA模式的CRUD增删改查
    @Autowired
    DemoEntityRepository demoEntityRepository;

    // 直接通过mongoTemplate提供的公共查询修改等操作，可以指定collection表
    @Resource
    private MongoTemplate mongoTemplate;


    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public Object getStudent(@RequestParam String id){
        return demoEntityRepository.findById(id);
    }

    @RequestMapping(value = "/demo", method = RequestMethod.POST)
    public String addStudent(@RequestBody DemoEntity demo){
        demoEntityRepository.save(demo);
        return "ok";
    }

    @RequestMapping(value = "/demo", method = RequestMethod.PUT)
    public String updateStudent(@RequestBody DemoEntity demo){
        demoEntityRepository.save(demo);
        return "ok";
    }

    @RequestMapping(value = "/demo", method = RequestMethod.DELETE)
    public String deleteStudent(@RequestParam String id){
        demoEntityRepository.deleteById(id);
        return "ok";
    }

    @RequestMapping(value = "/demo/criteria", method = RequestMethod.GET)
    public Object getStudentByCriteria(@RequestParam String id, @RequestParam String collectionName){
        Query query = new Query(Criteria.where("id").is(id));
        // 可以支持返回Object，所以这种方式可以写成一个公共的查询，通过指定表名来查询
        return mongoTemplate.find(query, Object.class, collectionName);
    }

    @RequestMapping(value = "/demo/criteria", method = RequestMethod.POST)
    public Object saveStudentByCriteria(@RequestBody Object demo, @RequestParam String collectionName){
        log.info("{}, collection: {}", demo , collectionName);
        // 可以支持储存Object，所以这种方式可以写成一个公共的存储，通过指定表名来存储
        return mongoTemplate.save(demo, collectionName);
    }
}
