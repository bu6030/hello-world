package com.test.mybatis.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = -7748312639957930069L;

    private String id;
    private String name;

    private StudentClass studentClass;
    private StudentSchool studentSchool;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentClass {
        private static final long serialVersionUID = -5213219387175188213L;
        private Long no;
        private String name;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentSchool {
        private static final long serialVersionUID = 3997395302485226876L;
        private Long sno;
        private String sname;
    }
}
