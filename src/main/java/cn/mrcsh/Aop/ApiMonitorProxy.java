package cn.mrcsh.Aop;

import cn.mrcsh.Annotations.APIMonitor;
import cn.mrcsh.Cache.APIInvokeCount;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class ApiMonitorProxy {

    @Pointcut("@annotation(cn.mrcsh.Annotations.APIMonitor)")
    public void APIPointCut() {
    }


    @Around("APIPointCut()")
    public Object Monitor(ProceedingJoinPoint point) throws Throwable {
        Object res;
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        String api = method.getAnnotation(APIMonitor.class).api();
        String parentAPI = method.getAnnotation(APIMonitor.class).parentAPI();
        if(api.equals("login")){
            APIInvokeCount.AllLoginCount++;
        }
        res = point.proceed();
        APIInvoke(parentAPI, api);
        return res;
    }

    public void APIInvoke(String parentAPI, String api) {
        Map<String, Map<String, Integer>> count = APIInvokeCount.count;
        if (count.containsKey(parentAPI)) {
            if (APIInvokeCount.count.get(parentAPI).containsKey(api)) {
                APIInvokeCount.count.get(parentAPI).replace(api, APIInvokeCount.count.get(parentAPI).get(api) + 1);
            } else {
                APIInvokeCount.count.get(parentAPI).put(api, 1);
            }
        } else {
            HashMap<String, Integer> map = new HashMap<>();
            map.put(api, 1);
            APIInvokeCount.count.put(parentAPI, map);
        }
    }


    @Before("APIPointCut()")
    public void before(){
        APIInvokeCount.AllCount++;
    }
}
