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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
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
        ids.clear();
//        log.info("authority_ids" + Arrays.toString(authority_ids.toArray()));
        roleConnectMapper.delete(new QueryWrapper<RoleConnect>().eq("role_id", role_id));

        for (Integer authorityId : authority_ids) {
            ids.add(authorityId);
            if (authorityMapper.selectId(authorityId) != 0) {
                getParentId(authorityId);
            }
        }
//        log.info(Arrays.toString(ids.toArray()));

        for (Integer integer : ids) {
            RoleConnect connect = new RoleConnect();
            connect.setEnable(true);
            connect.setAuthority_id(integer);
            connect.setRole_id(role_id);
            roleConnectMapper.insert(connect);
        }
        return null;
    }

    public Set<Integer> ids = new HashSet<>();

    public void getParentId(int auth_id) {
        Integer integer = authorityMapper.selectId(auth_id);
        if (integer != 0) {
            ids.add(integer);
            getParentId(integer);
        }
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
            if (a != null) {
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
//        log.info(Arrays.toString(authorities.toArray()));
//        log.info(Arrays.toString(authorities1.toArray()));
        for (Authority a : authorities1) {
//            log.info("1============================");
//            log.info(a.toString());
            for (Authority a1 : authorities) {
//                log.info(a1.toString());
                if (a.getName().equals(a1.getName())) {
                    a1.setEnable(true);
                    break;
                }
            }
//            log.info("2============================");
        }
//        log.info(Arrays.toString(authorities.toArray()));
        List<Integer> role_ids = new ArrayList<>();
        for (Authority authority : authorities) {
            if (authority.isEnable() && authority.getType().equals("2") || authority.isExclusions() && authority.isEnable()) {
                role_ids.add(authority.getId());
            }
        }
//        log.info(Arrays.toString(role_ids.toArray()));
        return role_ids;
    }

    public int insert(Authority authority) {
        return authorityMapper.insert(authority);
    }

    public int delete(int id) {
        del(id);
        roleConnectMapper.deleteByAuthority_id(id);
        return authorityMapper.deleteById(id);
    }

    public void del(int id) {
        List<Authority> authorities = authorityMapper.selectList(new QueryWrapper<Authority>().eq("level", id));
        List<Integer> authIds = authorities.stream().map(Authority::getId).collect(Collectors.toList());
        if (authIds.size() == 0) {
            return;
        }
        authorityMapper.deleteBatchIds(authIds);
        for (Integer authId : authIds) {
            roleConnectMapper.deleteByAuthority_id(authId);
            del(authId);
        }
    }

    public List<Authority> getAll(){
        return authorityMapper.selectList(null);
    }

    public int updateByAuthorityId(Authority authority){
        return authorityMapper.update(authority, new QueryWrapper<Authority>().eq("id", authority.getId()));
    }
}
