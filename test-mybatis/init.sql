-- SQLite 建表 SQL/初始化数据 SQL
CREATE TABLE student(
                        id         integer(10) not null,
                        name       varchar(32) not null,
                        class_no   integer,
                        class_name text,
                        school_no integer,
                        school_name integer);

CREATE TABLE school_bag (
                            ID INTEGER,
                            NAME VARCHAR,
                            STUDENT_ID INTEGER
);

CREATE TABLE teacher (
                         ID INTEGER,
                         NAME VARCHAR,
                         STUDENT_ID INTEGER
);

INSERT INTO student (id,name,class_no,class_name,school_no,school_name) VALUES
    (99,'小张张99',1,'一年一班',1,'测试学校');

INSERT INTO school_bag (ID,NAME,STUDENT_ID) VALUES
                                                (1,'书包 1',99),
                                                (2,'书包 2',99),
                                                (3,'书包 3',99);

INSERT INTO teacher (ID,NAME,STUDENT_ID) VALUES
    (1,'班主任 1',99);