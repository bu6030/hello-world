package com.example.demo.springboottest;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@ToString
@Entity
@Table(name = "student")
public class StudentEntity implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    public StudentEntity() {
    }
    public StudentEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
