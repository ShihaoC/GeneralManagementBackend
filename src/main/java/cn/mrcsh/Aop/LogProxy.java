package cn.mrcsh.Aop;

import cn.mrcsh.Annotations.Log;
import cn.mrcsh.Util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.ietf.jgss.Oid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Slf4j
@Component
public class LogProxy {

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("@annotation(cn.mrcsh.Annotations.Log)")
    public void pt(){}

    @Around("pt()")
    public Object Around(ProceedingJoinPoint point) throws Throwable {
        Object res;
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        //***********************************************************************************
        String api = method.getAnnotation(Log.class).api(); // api名称
        String module = method.getAnnotation(Log.class).module(); // 模块名称
        Long currentTime = System.currentTimeMillis(); // 时间戳
        String token = request.getHeader("authorization"); // token 用JwtUtil解析token
        redisUtil.set(currentTime+":["+module+"("+api+")]",request.getHeader("authorization"));
        log.info(request.getHeader("authorization"));










        //***********************************************************************************
        res = point.proceed();
        return res;
    }
}
