package cn.mrcsh.Seurity;

import cn.hutool.json.JSONUtil;
import cn.mrcsh.Entity.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset:UTF-8");
        ServletOutputStream sos = response.getOutputStream();
        Result result = Result.fail("用户名密码错误");
        sos.write(JSONUtil.toJsonStr(result).getBytes());
        sos.flush();
        sos.close();
    }
}
