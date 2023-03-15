package cn.mrcsh;

import love.forte.simboot.spring.autoconfigure.EnableSimbot;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("cn.mrcsh.Mapper")
@EnableTransactionManagement
@EnableAspectJAutoProxy
//@EnableSimbot
public class EmManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmManagementApplication.class, args);
    }

}
