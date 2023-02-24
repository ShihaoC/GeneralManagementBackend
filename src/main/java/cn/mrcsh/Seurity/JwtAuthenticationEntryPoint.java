package cn.mrcsh.Seurity;

import cn.hutool.json.JSONUtil;
import cn.mrcsh.Entity.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset:UTF-8");
        ServletOutputStream sos = response.getOutputStream();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Result result = Result.fail("认证失败，请先登录");
        sos.write(JSONUtil.toJsonStr(result).getBytes());
        sos.flush();
        sos.close();
    }
}
