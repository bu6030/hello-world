package com.example.demo;

import com.example.demo.springboottest.SpringBootTestRepository;
import com.example.demo.springboottest.StudentEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@Slf4j
@RunWith(SpringRunner.class)
// 引入了JPA环境
@DataJpaTest
// 引入了datasource，为JPA提供测试数据源
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {
    @Autowired
    SpringBootTestRepository repository;


    @Test
    public void testFindById()
    {
        // 测试查询之前先存入数据
        StudentEntity studentEntity = new StudentEntity("2", "张三");
        repository.save(studentEntity);
        // 测试查询结果数据并验证
        Optional<StudentEntity> opt = repository.findById("2");
        log.info("testFindById find by id result: {}", opt);
        Assert.assertEquals("2", opt.get().getId());
    }

    @Test
    public void testDeleteById()
    {
        // 测试查询之前先存入数据
        StudentEntity studentEntity = new StudentEntity("1", "张三");
        repository.save(studentEntity);
        // 验证数据库存在可以删除的数据
        Optional<StudentEntity> opt = repository.findById("1");
        log.info("testDeleteById find by id result: {}", opt);
        Assert.assertEquals("1", opt.get().getId());
        // 执行删除数据测试
        repository.deleteById("1");
        // 再次查询数据库中是否还存在数据并验证
        opt = repository.findById("1");
        log.info("testDeleteById find by id result after delete: {}", opt);
        Assert.assertFalse(opt.isPresent());
    }
}
