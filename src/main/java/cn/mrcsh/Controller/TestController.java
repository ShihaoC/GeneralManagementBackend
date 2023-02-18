package cn.mrcsh.Controller;

import cn.mrcsh.Annotations.APIMonitor;
import cn.mrcsh.Cache.APIInvokeCount;
import cn.mrcsh.Entity.Factory.Response;
import cn.mrcsh.Entity.Factory.ResponseFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口（忽略）
 */
@Deprecated
@RestController
@CrossOrigin
@RequestMapping("/test")
@APIMonitor(api = "test.all",value = "test")
public class TestController {
    @GetMapping("test1")
    public Response<String> test(){
        return new ResponseFactory<String>().getInstance("测试","测试",200);
    }
    @GetMapping("test2")
    public Object test2(){
        return APIInvokeCount.count;
    }
}
