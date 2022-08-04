package com.amela.managertaskamela;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.amela.managertaskamela.repository")
public class ManagerTaskAmelaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerTaskAmelaApplication.class, args);
    }

}
