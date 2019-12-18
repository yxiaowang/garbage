package com.njupt.garbage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.njupt.garbage.mapper")
public class GarbageApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarbageApplication.class, args);
    }

}
