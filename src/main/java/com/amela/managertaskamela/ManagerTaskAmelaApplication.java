package com.amela.managertaskamela;

import com.amela.managertaskamela.aspect.TaskAspect;
import com.amela.managertaskamela.handler.TaskExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan(basePackages = "com.amela.managertaskamela.repository")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ManagerTaskAmelaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerTaskAmelaApplication.class, args);
    }
    @Bean
    public TaskAspect taskAspect() {
        return new TaskAspect();
    }

    @Bean
    public TaskExceptionHandler taskExceptionHandler() {
        return new TaskExceptionHandler();
    }
}
