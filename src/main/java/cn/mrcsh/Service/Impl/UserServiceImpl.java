package cn.mrcsh.Service.Impl;

import cn.mrcsh.Code.ErrorCode;
import cn.mrcsh.Entity.Factory.PagesFactory;
import cn.mrcsh.Entity.Factory.Response;
import cn.mrcsh.Entity.Factory.ResponseFactory;
import cn.mrcsh.Entity.User;
import cn.mrcsh.Enum.ROLE;
import cn.mrcsh.Mapper.UserMapper;
import cn.mrcsh.Service.UserService;
import cn.mrcsh.Util.JwtUtil;
import cn.mrcsh.Util.Md5Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    /**
     * 登录方法
     *
     * @param user 表单的user对象
     * @return Response<User>
     */
    @Transactional
    @Override
    public Response<User> login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .eq("username", user.getUsername())
                .eq("password", Md5Util.generateMd5Hex(user.getPassword()));
        User resultUser = mapper.selectOne(wrapper);
        if (resultUser == null) {
            return new ResponseFactory<User>().getInstance(null, "用户名密码不正确", ErrorCode.ERROR);
        }
        String token = JwtUtil.getToken(resultUser.getUsername(), ROLE.valueOf(resultUser.getRole()));
        resultUser.setToken(token);
        return new ResponseFactory<User>().getInstance(resultUser, "success", ErrorCode.SUCCESS);
    }

    /**
     * 注册方法
     *
     * @param user 表单的user对象
     * @return Response<String>
     */
    @Transactional
    @Override
    public Response<String> register(User user) {
        if (user == null) {
            return new ResponseFactory<String>().getInstance("表单内容为空", "error", ErrorCode.ERROR);
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",user.getUsername());

        User queryResult = mapper.selectOne(wrapper);

        if(queryResult != null){
            return new ResponseFactory<String>().getInstance("已经存在","已经存在", ErrorCode.IS_EXISTS);
        }

        user.setRole(ROLE.STAFF.toString());
        user.setDepartment("DEFAULT");
        user.setPassword(Md5Util.generateMd5Hex(user.getPassword()));
        user.setNick_name("nick");
        int insert = mapper.insert(user);
        if (insert > 0) {
            return new ResponseFactory<String>().getInstance("注册成功", "success", ErrorCode.SUCCESS);
        }
        return new ResponseFactory<String>().getInstance("注册失败", Objects.requireNonNull(user).getUsername() == null ? "用户名不能为空" : "密码不能为空", ErrorCode.ERROR);
    }

    @Override
    public Response<Object> selectList(String search, int page) {
        Page<User> userPage = new Page<>(page, 10);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .like("username", Objects.requireNonNull(search));
        Page<User> userPage1 = null;
        try {
            userPage1 = mapper.selectPage(userPage, wrapper);
        } catch (Exception e) {
            new ResponseFactory<>().getInstance(e.getMessage(), "查询失败", ErrorCode.ERROR);
        }
        if(userPage1 != null){
            return new ResponseFactory<>().getInstance(new PagesFactory<>().getInstance(userPage1.getCurrent(), userPage1.getTotal(), userPage1.getRecords()),"查询成功",ErrorCode.SUCCESS);
        }else {
            return null;
        }
    }

    @Override
    public Response<Object> delete(int id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .eq("id",id);
        int delete;
        try {
            delete = mapper.delete(wrapper);
        } catch (Exception e) {
            return new ResponseFactory<>().getInstance(e.getMessage(),"删除失败",ErrorCode.ERROR);
        }
        if(delete > 0){
            return new ResponseFactory<>().getInstance(delete,"删除成功",ErrorCode.SUCCESS);
        }
        return new ResponseFactory<>().getInstance(delete,"没有这个用户",ErrorCode.NOT_EXISTS);
    }

    @Override
    public Response<Object> update(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",user.getId());
        int result;
        try {
            result = mapper.update(user,wrapper);
        } catch (Exception e) {
            return new ResponseFactory<>().getInstance(e.getMessage(),"修改失败",ErrorCode.ERROR);
        }
        if(result > 0){
            return new ResponseFactory<>().getInstance(result,"修改成功",ErrorCode.SUCCESS);
        }
        return new ResponseFactory<>().getInstance(result,"没有这个用户",ErrorCode.NOT_EXISTS);
    }

    @Override
    public Response<Object> insert(User user) {
        int result;
        if(user != null){
            try {
                result = mapper.insert(user);
            } catch (Exception e) {
                return new ResponseFactory<>().getInstance(e.getMessage(),"添加失败",ErrorCode.ERROR);
            }
        }else {
            return new ResponseFactory<>().getInstance("表单为空","表单为空",ErrorCode.NOT_EXISTS);
        }
        return new ResponseFactory<>().getInstance(result,"添加成功",ErrorCode.SUCCESS);
    }
}
