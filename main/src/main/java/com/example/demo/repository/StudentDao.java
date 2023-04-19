package com.example.demo.repository;//package com.example.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class StudentDao {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//    public List getStudent(String id) {
//        List students = jdbcTemplate.queryForList("select * from student where id = ?", id);
//        return students;
//    }
//
//    public void saveStudent(String id, String name) {
//        StringBuffer sql = new StringBuffer();
//        sql.append(" insert into student ");
//        sql.append(" (id,name) ");
//        sql.append(" values ");
//        sql.append(" (?,?) ");
//        jdbcTemplate.update(sql.toString(), new Object[] { id, name });
//    }
//
//    public void updateStudent(String id, String name) {
//        StringBuffer sql = new StringBuffer();
//        sql.append(" update student ");
//        sql.append(" set name = ? ");
//        sql.append(" where id = ? ");
//        jdbcTemplate.update(sql.toString(), new Object[] { name, id });
//    }
//
//    public void deleteStudent(String id) {
//        StringBuffer sql = new StringBuffer();
//        sql.append(" delete from student ");
//        sql.append(" where id = ? ");
//        jdbcTemplate.update(sql.toString(), new Object[] { id });
//    }
//}
