package cn.mrcsh.Controller;

import cn.mrcsh.Entity.User;
import cn.mrcsh.Mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserMapper mapper;

    @GetMapping("/login")
    public User login(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username","admin")
                        .eq("password","123456");
        return mapper.selectOne(wrapper);
    }
}
