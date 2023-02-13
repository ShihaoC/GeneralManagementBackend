package cn.mrcsh.Controller;

import cn.mrcsh.Entity.Factory.Response;
import cn.mrcsh.Entity.User;
import cn.mrcsh.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
@Slf4j
public class UserController {

    @Autowired
    private UserService service;

    /**
     * 登录接口
     * @param user POST请求的参数
     * @return 通用返回体
     */
    @PostMapping("/login")
    public Response<User> login(@RequestBody User user) {
        return service.login(user);
    }

    /**
     * 注册接口
     * @param user POST 传入user对象
     * @return 通用返回体
     */
    @PostMapping("/register")
    public Response<String> register(@RequestBody User user){
        return service.register(user);
    }

}
