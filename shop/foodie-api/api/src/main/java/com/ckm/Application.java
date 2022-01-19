package com.ckm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.ckm.dao")  //mapper的dao扫描
@ComponentScan(basePackages = {"com.ckm","org.n3r.idworker"})
//@EnableTransactionManagement  //在SpringBootApplication已经自动注入了事务，所以可以不用
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
