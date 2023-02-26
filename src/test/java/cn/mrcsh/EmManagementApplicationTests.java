package cn.mrcsh;

import cn.mrcsh.Entity.Authority;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Entity.TreeNode;
import cn.mrcsh.Service.Impl.AuthorityService;
import cn.mrcsh.Util.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class EmManagementApplicationTests {
    @Autowired
    AuthorityService service;

    @Test
    void contextLoads(){

    }

    @Test
    void simpleTest(){

    }

}
