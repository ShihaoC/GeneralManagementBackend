package cn.mrcsh;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.mrcsh.Entity.Employee;
import cn.mrcsh.Util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
@Slf4j
class EmManagementApplicationTests {


    @Test
    void contextLoads(){

    }

    @Test
    void simpleTest(){
//        HttpRequest request = new HttpRequest("http://localhost:8848/test/test2");
//        Map<String,String> map = new HashMap<>();
//        map.put("user","admin");
//        request.addHeaders(map);
//        System.out.println(request.execute().body());
    }

}
