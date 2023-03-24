package cn.mrcsh.Service.Impl;

import cn.mrcsh.Entity.Factory.PagesFactory;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Entity.Role;
import cn.mrcsh.Entity.User;
import cn.mrcsh.Enum.ROLE;
import cn.mrcsh.Mapper.RoleMapper;
import cn.mrcsh.Mapper.UserMapper;
import cn.mrcsh.Service.UserService;
import cn.mrcsh.Util.JwtUtil;
import cn.mrcsh.Util.Md5Util;
import cn.mrcsh.Util.PoiUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * 登录方法
     *
     * @param user 表单的user对象
     * @return Response<User>
     */
    @Transactional
    @Override
    public User login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .eq("username", user.getUsername())
                .eq("password", Md5Util.generateMd5Hex(user.getPassword()));
        User resultUser = mapper.selectOne(wrapper);
        if (resultUser == null) {

        }
        String token = JwtUtil.getToken(resultUser.getUsername(), ROLE.valueOf(resultUser.getRole()));
        resultUser.setToken(token);
        return resultUser;
    }

    /**
     * 注册方法
     *
     * @param user 表单的user对象
     * @return Response<String>
     */
    @Transactional
    @Override
    public Result register(User user) {
        if (user == null) {
           return Result.fail("表单内容为空");
        }
        user.setUsed(true);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",user.getUsername());

        User queryResult = mapper.selectOne(wrapper);

        if(queryResult != null){
            return Result.fail("已经存在");
        }

        user.setRole("2");
        user.setImage_url("https://csh-test1.oss-cn-beijing.aliyuncs.com/user-fill.png");
        if(user.getDepartment() == null){
            user.setDepartment("无");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setNick_name("nick");
        int insert = mapper.insert(user);
        if (insert > 0) {
            return Result.success("注册成功");
        }
        return Result.fail("注册失败");

    }

    @Override
    public Result selectList(String search, int page) {
        Page<User> userPage = new Page<>(page, 10);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .like("username", Objects.requireNonNull(search));
        Page<User> userPage1 = null;
        List<Role> roles = roleMapper.selectList(null);

        List<User> users;

        try {
            userPage1 = mapper.selectPage(userPage, wrapper);
            users = userPage.getRecords();
            for (User user : users) {
                for (Role role : roles) {
                    if(Integer.parseInt(user.getRole()) == role.getId()){
                        user.setRole(role.getNick());
                        break;
                    }
                }
            }

        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
        if(userPage1 != null){
            return Result.success(new PagesFactory<>().getInstance(userPage1.getCurrent(), userPage1.getTotal(), users));
        }else {
            return null;
        }
    }

    @Override
    public Result delete(int id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .eq("id",id);
        int delete;
        try {
            delete = mapper.delete(wrapper);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
        if(delete > 0){
            return Result.success("删除成功");
        }
        return Result.fail("没有这个用户");
    }

    @Override
    public Result update(User user) {
//        log.info(user.toString());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",user.getId());
        int result;
        try {
            result = mapper.update(user,wrapper);
        } catch (Exception e) {
            return Result.fail(e.getMessage());

        }
        if(result > 0){
            return Result.success("修改成功");
        }
        return Result.fail("没有这个用户");
    }

    @Override
    public Result insert(User user) {
        int result;
        if(user != null){
            try {
                result = mapper.insert(user);
            } catch (Exception e) {
                return Result.fail(e.getMessage());

            }
        }else {
            return Result.fail("表单为空");
        }
        return Result.success(result);

    }

    @Override
    public Result update_statue(int id, boolean flag) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        try {
            userQueryWrapper.eq("id",id);
        } catch (Exception e) {
            return Result.fail("添加失败");
        }
        User user = mapper.selectOne(userQueryWrapper);
        user.setUsed(flag);
        int update;
        try {
            update = mapper.update(user, userQueryWrapper);
        } catch (Exception e) {
            return Result.fail(e.getMessage());

        }
        if(update > 0){
            return Result.success(update);

        }
        return Result.fail("修改失败");
    }

    @Override
    public void export_excel(HttpServletResponse response) {
        PoiUtil.export_Excel(response,mapper,User.class);
    }

    @Override
    public User getSimple(int id) {
        return mapper.selectOne(new QueryWrapper<User>().eq("id", id));
    }

    @Override
    public int batch_Delete(List<User> users) {
        List<Integer> userids = users.stream().map(User::getId).collect(Collectors.toList());
        return mapper.deleteBatchIds(userids);
    }


}
