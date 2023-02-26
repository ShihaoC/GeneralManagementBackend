package cn.mrcsh.Controller;

import cn.mrcsh.Cache.APIInvokeCount;
import cn.mrcsh.Entity.Authority;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Entity.RoleConnect;
import cn.mrcsh.Entity.TreeNode;
import cn.mrcsh.Mapper.RoleConnectMapper;
import cn.mrcsh.Service.Impl.AuthorityService;
import cn.mrcsh.Util.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public Result test() {
        return Result.success("测试");
    }


    @GetMapping("test2")
    public Result test2() {
        return null;
    }
}
