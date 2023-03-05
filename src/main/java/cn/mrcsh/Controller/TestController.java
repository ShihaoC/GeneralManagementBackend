package cn.mrcsh.Controller;

import cn.mrcsh.Annotations.Log;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Mapper.RoleConnectMapper;
import cn.mrcsh.Service.Impl.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 测试接口（忽略）
 */
@Deprecated
@RestController
@CrossOrigin
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private AuthorityService service;

    @Autowired
    private RoleConnectMapper mapper;

    @GetMapping("test1")
    @Log(module = "Test",api = "test1")
    public Result test() {
        return Result.success("测试");
    }


    @GetMapping("test2")
    public Result test2() {
        return null;
    }
}
