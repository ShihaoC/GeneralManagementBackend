package cn.mrcsh.Seurity;

import cn.mrcsh.Entity.Authority;
import cn.mrcsh.Entity.Role;
import cn.mrcsh.Entity.User;
import cn.mrcsh.Mapper.AuthorityMapper;
import cn.mrcsh.Mapper.RoleConnectMapper;
import cn.mrcsh.Mapper.RoleMapper;
import cn.mrcsh.Mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleConnectMapper roleConnectMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);

        return new AccountUser(user.getId(), user.getUsername(), user.getPassword(), getUserAuthority(user.getId()));
    }

    public List<GrantedAuthority> getUserAuthority(Integer userId) {
        log.info("ID" + userId);
        StringBuilder authority = new StringBuilder();
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
        String role_id = user.getRole();
        List<Role> roles = roleMapper.selectList(new QueryWrapper<Role>().eq("id", role_id));
        if(roles.size() > 0){
            authority = new StringBuilder(roles.stream().map(e -> "ROLE_" + e.getRole_name()).collect(Collectors.joining(",")));
        }

        List<Authority> authorities = roleConnectMapper.list(Integer.parseInt(role_id));
        authority.append(",");
        if(authorities.size() > 0){
            for (Authority e : authorities) {
                if(e.isEnable()){
                    authority.append(",").append(e.getValue());
                }
            }
        }
        log.info("AUTH:::"+authority);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority.toString());
    }
}
