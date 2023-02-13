package cn.mrcsh;

import cn.mrcsh.Entity.Factory.ResponseFactory;
import cn.mrcsh.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class EmManagementApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(String.format("INSERT INTO test(id,name,pwd) values(\"%s\",\"%s\",\"%s\")", "1", "zhangsan", "123"));
    }
}
