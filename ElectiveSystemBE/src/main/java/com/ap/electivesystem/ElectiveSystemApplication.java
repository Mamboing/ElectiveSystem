package com.ap.electivesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ap.electivesystem.mapper")
public class ElectiveSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectiveSystemApplication.class, args);
    }

}
