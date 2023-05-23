package cn.mrcsh;

import cn.hutool.http.HttpUtil;
import cn.mrcsh.Entity.*;
import cn.mrcsh.Robot.DingTalk.DingTalkUtil;
import cn.mrcsh.Service.Impl.AuthorityService;
import cn.mrcsh.Service.Impl.NoticeServiceImpl;
import cn.mrcsh.Util.OSSUtil;
import cn.mrcsh.Util.RedisUtil;
import cn.mrcsh.Util.TreeUtil;
import com.alibaba.fastjson2.JSON;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
class EmManagementApplicationTests {
//    @Autowired
//    AuthorityService service;

//    @Test
//    void contextLoads(){
//        service.getParentId(8);
//        log.info(Arrays.toString(service.ids.toArray()));
//    }

//    @Autowired
//    private RedisUtil redis;
//
//    @Autowired
//    private NoticeServiceImpl service;

    @Test
    void simpleTest() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, ApiException {
        DingTalkUtil.DingTalkMessageBuilder builder = new DingTalkUtil.DingTalkMessageBuilder();
        builder
                .initialization()
                .setAt(true)
//                .setMarkdown("员工管理系统","# 项目地址 \n\n [项目地址](https://oa.mrcsh.cn)")
//                .setText("123")
                .setLink("https://oa.mrcsh.cn","https://csh-test1.oss-cn-beijing.aliyuncs.com/a1c3c25d06f54ae8bc365381bdf8c5d6.png","测试","123123")
                .build()
                .sendMessage();
    }

    @Test
    void StreamTest() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, ApiException {
        DingTalkUtil.DingTalkMessageBuilder builder = new DingTalkUtil.DingTalkMessageBuilder();
        builder
                .initialization()
                .setText("Hello")
                .build()
                .sendMessage();
    }

    @Test
    void ServerInfoTest(){
        System.out.println(JSON.parseObject(HttpUtil.get(String.format("https://api.mrcsh.cn/api/querymcserver?ServerIP=%s","mc.mrcsh.cn")),CurrentServer.class).getFavicon());
    }

    @Autowired
    private OSSUtil util;
    @Test
    void Testss(){
        util.test();
    }

}
