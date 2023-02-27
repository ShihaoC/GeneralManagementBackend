package cn.mrcsh.Seurity;

import cn.hutool.json.JSONUtil;
import cn.mrcsh.Cache.APIInvokeCount;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Entity.User;
import cn.mrcsh.Mapper.UserMapper;
import cn.mrcsh.Util.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset:UTF-8");
        ServletOutputStream sos = response.getOutputStream();
        // 生成JWT 并放入到请求头中
        String jwt = JwtUtil.generateToken(authentication.getName());
        APIInvokeCount.AllLoginCount++;
        User user = mapper.selectOne(new QueryWrapper<User>().eq("username", authentication.getName()));
        response.setHeader("Authorization",jwt);
        response.setHeader("image",user.getImage_url());
        Result result = Result.success("登录成功");
        sos.write(JSONUtil.toJsonStr(result).getBytes());
        sos.flush();
        sos.close();
    }
}
