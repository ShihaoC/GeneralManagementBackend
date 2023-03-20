package cn.mrcsh.Controller;

import cn.mrcsh.Annotations.APIMonitor;
import cn.mrcsh.Annotations.Log;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Entity.TreeNode;
import cn.mrcsh.Entity.User;
import cn.mrcsh.Service.Impl.AuthorityService;
import cn.mrcsh.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 注册接口
     *
     * @param user POST 传入user对象
     * @return 通用返回体
     */
    @Log(module = "用户模块",api = "注册")
    @APIMonitor(api = "register", parentAPI = "auth")
    @PostMapping("/register")
    public Object register(@RequestBody User user) {

        return service.register(user);
    }

    /**
     * 获取权限列表
     *
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
     * @param authority_ids 权限ids
     * @param role_id       角色id
     * @return 返回体
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('system:authority:update')")
    @Log(module = "用户模块",api = "修改权限")
    @Transactional // 事务
    public Object update(@RequestBody List<Integer> authority_ids, int role_id) {
        log.info(Arrays.toString(authority_ids.toArray()));
        return authorityService.update(authority_ids, role_id);
    }

    /**
     * 默认选中
     *
     * @param role_id 角色id
     * @return 返回体
     */
    @GetMapping("/default_check")
    @PreAuthorize("hasAuthority('system:authority:query')")
    public Object defaultCheck(int role_id) {
        List<Integer> defaultChecked = authorityService.getDefaultChecked(role_id);
        return Result.success(defaultChecked);
    }
    @GetMapping("/updatePassword/{user_id}")
    public Object updatePassword(@PathVariable String user_id,String pass1,String pass2){
        User simple = service.getSimple(Integer.parseInt(user_id));
        if(simple == null){
            return Result.fail("不存在此用户");
        }
        if(!simple.getPassword().equals(bCryptPasswordEncoder.encode(pass1))){
            return Result.fail("旧密码错误");
        }
        simple.setPassword(bCryptPasswordEncoder.encode(pass2));
        service.update(simple);
        return Result.success("修改成功");
    }
}
