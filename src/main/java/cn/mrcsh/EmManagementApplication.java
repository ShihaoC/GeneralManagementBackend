package cn.mrcsh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.mrcsh.Mapper")
public class EmManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmManagementApplication.class, args);
    }

}
