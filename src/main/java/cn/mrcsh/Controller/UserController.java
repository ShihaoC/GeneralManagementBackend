package cn.mrcsh.Controller;

import cn.mrcsh.Entity.Factory.Response;
import cn.mrcsh.Entity.User;
import cn.mrcsh.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户接口
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService service;

    /**
     * 登录方法
     * @param user POST请求的参数
     * @return 通用返回体
     */
    @PostMapping("/login")
    public Response<User> login(User user) {
        return service.login(user);
    }

    /**
     * 注册方法
     * @param user POST 传入user对象
     * @return 通用返回体
     */
    @PostMapping("/register")
    public Response<String> register(User user){
        return service.register(user);
    }

}
