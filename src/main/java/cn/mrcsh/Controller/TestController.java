package cn.mrcsh.Controller;

import cn.mrcsh.Cache.APIInvokeCount;
import cn.mrcsh.Entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试接口（忽略）
 */
@Deprecated
@RestController
@CrossOrigin
@RequestMapping("/test")
@Slf4j
public class TestController {
    @GetMapping("test1")
    @PreAuthorize("hasRole('admin')")
    public Result test(){
        return Result.success("测试");
    }
    @GetMapping("test2")
    @PreAuthorize("hasAuthority('system:employee:list')")
    public Object test2(HttpServletRequest request){
        log.info(request.getHeader("user"));
        return APIInvokeCount.count;
    }
}
