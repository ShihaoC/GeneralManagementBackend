package cn.mrcsh.Service.Impl;

import cn.mrcsh.Entity.*;
import cn.mrcsh.Mapper.AuthorityMapper;
import cn.mrcsh.Mapper.RoleConnectMapper;
import cn.mrcsh.Mapper.RoleMapper;
import cn.mrcsh.Mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class AuthorityService {

    @Autowired
    private RoleMapper mapper;

    @Autowired
    private RoleConnectMapper roleConnectMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private UserMapper userMapper;

    public Result insert(Role role) {
        mapper.insert(role);
        Role selectOne = mapper.selectOne(new QueryWrapper<Role>().eq("role_name", role.getRole_name()));
        RoleConnect connect = new RoleConnect();
        connect.setRole_id(selectOne.getId());
        connect.setAuthority_id(1);
        return Result.success(roleConnectMapper.insert(connect));
    }

    public Result update(List<Authority> list, int role_id) {
        AtomicInteger result = new AtomicInteger();
        list.forEach((e)->{
            log.info("role_id::"+role_id);
            log.info("authority_id"+e.getId()+"\t"+e.getValue());
            RoleConnect connect = roleConnectMapper.selectOne(new QueryWrapper<RoleConnect>().eq("role_id",role_id).eq("authority_id",e.getId()));
            if(e.isEnable()){
                connect.setEnable(true);
            }else {
                connect.setEnable(false);
            }
            roleConnectMapper.update(connect,new QueryWrapper<RoleConnect>().eq("role_id",role_id).eq("authority_id",e.getId()));
        });
        return Result.success(result.get());
    }


    public Result selectAll(int role_id) {
        List<Authority> authorities = roleConnectMapper.list(role_id);
        return Result.success(authorities);
    }
}
