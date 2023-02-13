package cn.mrcsh.Controller;

import cn.mrcsh.Entity.Factory.Response;
import cn.mrcsh.Entity.Factory.ResponseFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class testController {
    @GetMapping("test1")
    public Response<String> test(){
        return new ResponseFactory<String>().getInstance("测试","测试",200);
    }
}
