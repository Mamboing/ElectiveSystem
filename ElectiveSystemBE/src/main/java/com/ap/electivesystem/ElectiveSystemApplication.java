package com.ap.electivesystem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ElectiveSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectiveSystemApplication.class, args);
        log.info("ElectiveSystemServer启动成功");
    }

}
