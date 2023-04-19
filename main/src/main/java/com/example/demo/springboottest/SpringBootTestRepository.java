package com.example.demo.springboottest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringBootTestRepository extends JpaRepository<StudentEntity, String> {
    Optional<StudentEntity> findById(String id);
    void deleteById(String id);
}
