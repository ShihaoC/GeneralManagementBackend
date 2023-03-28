package cn.mrcsh.Seurity;

import cn.mrcsh.Entity.User;
import cn.mrcsh.Mapper.UserMapper;
import cn.mrcsh.Service.UserService;
import cn.mrcsh.Util.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private UserMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwt = request.getHeader("Authorization");
        if (jwt == null || jwt.equals("undefined") || jwt.equals("")) {
            chain.doFilter(request, response);
            return;
        }

        Claims claims = JwtUtil.checkToken(jwt);
        if (claims == null) {
            throw new JwtException("token 异常");
        }
        if (JwtUtil.isTokenExpired(claims)) {
            throw new JwtException("token 过期");
        }
        String username = claims.getSubject();
        User user = mapper.selectOne(new QueryWrapper<User>().eq("username", username));

        // 获取用户权限等信息
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(username, null, userDetailsService.getUserAuthority(user.getId()));
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }
}
