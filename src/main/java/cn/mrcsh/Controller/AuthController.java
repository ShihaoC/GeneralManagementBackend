package cn.mrcsh.Controller;

import cn.mrcsh.Annotations.APIMonitor;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Entity.TreeNode;
import cn.mrcsh.Entity.User;
import cn.mrcsh.Service.Impl.AuthorityService;
import cn.mrcsh.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户接口
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthorityService authorityService;

    /**
     * 登录接口
     *
     * @param user POST请求的参数
     * @return 通用返回体
     */
    @APIMonitor(api = "login", parentAPI = "auth")
    @PostMapping("/login")
    public Object login(User user) {
        return service.login(user);
    }

    /**
     * 注册接口
     *
     * @param user POST 传入user对象
     * @return 通用返回体
     */
    @APIMonitor(api = "register", parentAPI = "auth")
    @PostMapping("/register")
    public Object register(@RequestBody User user) {

        return service.register(user);
    }

    /**
     * 获取权限列表
     * @param role_id @de
     * @return 返回体
     */
    @GetMapping("/getAuthority")
    @PreAuthorize("hasAuthority('system:authority:query')")
    public Object Author(int role_id) {
        List<TreeNode> treeNodes = authorityService.selectAll(role_id);
        return Result.success(treeNodes);
    }

    /**
     *
     * @param authority_ids 权限ids
     * @param role_id 角色id
     * @return 返回体
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('system:authority:update')")
    public Object update(@RequestBody List<Integer> authority_ids, int role_id){
        return authorityService.update(authority_ids, role_id);
    }

    /**
     * 默认选中
     * @param role_id 角色id
     * @return 返回体
     */
    @Transactional // 事务，防止服务器宕机数据安全性
    @GetMapping("/default_check")
    @PreAuthorize("hasAuthority('system:authority:query')")
    public Object defaultCheck(int role_id){
        List<Integer> defaultChecked = authorityService.getDefaultChecked(role_id);
        return Result.success(defaultChecked);
    }
}
