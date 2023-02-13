package cn.mrcsh.Service;

import cn.mrcsh.Entity.Factory.Response;
import cn.mrcsh.Entity.Factory.ResponseFactory;
import cn.mrcsh.Entity.User;
import cn.mrcsh.Enum.DEPARTMENT;
import cn.mrcsh.Enum.ROLE;
import cn.mrcsh.Mapper.UserMapper;
import cn.mrcsh.Util.JwtUtil;
import cn.mrcsh.Util.Md5Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper mapper;

    /**
     * 登录方法
     *
     * @param user 表单的user对象
     * @return Response<User>
     */
    @Transactional
    public Response<User> login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername())
                .eq("password", Md5Util.generateMd5Hex(user.getPassword()));
        User resultUser = mapper.selectOne(wrapper);
        if (resultUser == null) {
            return new ResponseFactory<User>().getInstance(null, "用户名密码不正确", 403);
        }
        String token = JwtUtil.getToken(resultUser.getUsername(), ROLE.valueOf(resultUser.getRole()));
        resultUser.setToken(token);
        return new ResponseFactory<User>().getInstance(resultUser, "success", 200);
    }

    /**
     * 注册方法
     *
     * @param user 表单的user对象
     * @return Response<String>
     */
    @Transactional
    public Response<String> register(User user) {
        if (user == null) {
            return new ResponseFactory<String>().getInstance("表单内容为空", "error", 500);
        }
        user.setRole(ROLE.STAFF.toString());
        user.setDepartment(DEPARTMENT.Publicity.toString());
        user.setPassword(Md5Util.generateMd5Hex(user.getPassword()));
        int insert = mapper.insert(user);
        if (insert > 0) {
            return new ResponseFactory<String>().getInstance("注册成功", "success", 200);
        }
        return new ResponseFactory<String>().getInstance("注册失败", Objects.requireNonNull(user).getUsername() == null ? "用户名不能为空" : "密码不能为空", 403);
    }
}
