package cn.mrcsh.Controller;

import cn.mrcsh.Annotations.APIMonitor;
import cn.mrcsh.Entity.Authority;
import cn.mrcsh.Entity.Role;
import cn.mrcsh.Entity.User;
import cn.mrcsh.Service.Impl.AuthorityService;
import cn.mrcsh.Service.UserService;
import cn.mrcsh.Util.JwtUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/getAuthority")
    public Object Author(int role_id) {
        return authorityService.selectAll(role_id);
    }

    @PostMapping("/insertAuthority")
    public Object insert(Role role) {
        return authorityService.insert(role);
    }

    @PostMapping("/update")
    public Object update(@RequestBody List<Authority> list,int role_id){
        return authorityService.update(list, role_id);
    }

}
