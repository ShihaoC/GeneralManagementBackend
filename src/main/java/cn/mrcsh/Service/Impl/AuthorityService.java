package cn.mrcsh.Service.Impl;

import cn.mrcsh.Entity.*;
import cn.mrcsh.Mapper.AuthorityMapper;
import cn.mrcsh.Mapper.RoleConnectMapper;
import cn.mrcsh.Mapper.RoleMapper;
import cn.mrcsh.Mapper.UserMapper;
import cn.mrcsh.Util.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


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

    /**
     * 修改角色权限
     *
     * @param authority_ids 权限ids
     * @param role_id       角色id
     * @return true/false
     */
    public Result update(List<Integer> authority_ids, int role_id) {
        Set<Integer> set = new HashSet<>();
        for (Integer authorityId : authority_ids) {
//            log.info("" + authorityId);
            roleConnectMapper.delete(new QueryWrapper<RoleConnect>().eq("role_id", role_id));
        }

        for (Integer authorityId : authority_ids) {
            set.add(authorityId);
            if(authorityMapper.selectId(authorityId) != 0){
                set.add(authorityMapper.selectId(authorityId));
            }
        }

        for (Integer integer : set) {
            RoleConnect connect = new RoleConnect();
            connect.setEnable(true);
            connect.setAuthority_id(integer);
            connect.setRole_id(role_id);
            roleConnectMapper.insert(connect);
        }
        return null;
    }


    /**
     * 获取树形结构
     *
     * @param role_id role_id
     * @return 树形结构
     */
    public List<TreeNode> selectAll(int role_id) {
        List<TreeNode> treeNodes = new ArrayList<>();
        List<Authority> authorities = selectList();
        List<Authority> authorities1 = selectAuth(role_id);
        for (Authority a : authorities1) {
            if(a != null){
                a.setEnable(false);
                for (Authority a1 : authorities) {
                    if (a.getName().equals(a1.getName())) {
                        a1.setEnable(true);
                        break;
                    }
                }
            }
        }
        for (Authority authority : authorities) {
            treeNodes.add(new TreeNode(authority.getId(), authority.getLevel(), authority.getName(), authority.isEnable(), authority));
        }
        TreeUtil treeUtil = new TreeUtil(treeNodes);
        List<TreeNode> treeNodes1 = treeUtil.buildTree();
        return treeNodes1;
    }


    public List<Authority> selectList() {
        return authorityMapper.selectList(null);
    }


    public List<Authority> selectAuth(int role_id) {
        List<RoleConnect> roleId = roleConnectMapper.selectList(new QueryWrapper<RoleConnect>().eq("role_id", role_id));
        List<Integer> collect = roleId.stream().map(RoleConnect::getAuthority_id).collect(Collectors.toList());
        List<Authority> authorities = new ArrayList<>();
        for (Integer integer : collect) {
            authorities.add(authorityMapper.selectOne(new QueryWrapper<Authority>().eq("id", integer)));
        }
        return authorities;
    }

    /**
     * 获取默认选中状态
     *
     * @param role_id 角色id
     * @return authorityIds
     */
    public List<Integer> getDefaultChecked(int role_id) {
        List<Authority> authorities = selectList();
        List<Authority> authorities1 = selectAuth(role_id);
        for (Authority a : authorities1) {
            for (Authority a1 : authorities) {
                if (a.getName().equals(a1.getName())) {
                    a1.setEnable(true);
                    break;
                }
            }
        }
        List<Integer> role_ids = new ArrayList<>();
        for (Authority authority : authorities) {
            if (authority.isEnable() && authority.getType().equals("2") || authority.getValue().equals("index")) {
                role_ids.add(authority.getId());
            }
        }
        return role_ids;
    }


}
