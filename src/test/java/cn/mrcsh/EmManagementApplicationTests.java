package cn.mrcsh;

import cn.mrcsh.Entity.Employee;
import cn.mrcsh.Util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

//@SpringBootTest
@Slf4j
class EmManagementApplicationTests {


    @Test
    void contextLoads(){

    }

    @Test
    void simpleTest(){
        ReflectUtil.forEachFields(Employee.class,(e)->{
            System.out.println(e.getName());
        });
    }

}
