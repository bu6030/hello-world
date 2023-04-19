package com.mongo.repository;

import com.mongo.entity.DemoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

// 类似与JPA的方式的接口，封装了各种findBy，findAll，save等基础方法
public interface DemoEntityRepository extends MongoRepository<DemoEntity, String> {
}