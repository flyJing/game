package com.thejing.student;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.thejing.student.mapper")
public class StudentApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class,args);
    }

}
