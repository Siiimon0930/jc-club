package com.jc.subject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: subject的启动类
 * @Author: Siiimon
 * @Date: 2023/12/15/15:21
 */
@SpringBootApplication
@ComponentScan("com.jc")
@MapperScan("com.jc.**.mapper")
public class SubjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubjectApplication.class);
    }
}
