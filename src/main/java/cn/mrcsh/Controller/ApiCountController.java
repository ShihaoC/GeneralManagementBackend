package cn.mrcsh.Controller;

import cn.mrcsh.Annotations.APIMonitor;
import cn.mrcsh.Cache.APIInvokeCount;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * API监控
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiCountController {
    /**
     * 获取所有API历史次数
     * @return
     */
    @APIMonitor(value = "count",parentAPI = "api")
    @GetMapping("/count")
    public List<Integer> getApiCount(){
        return APIInvokeCount.counts;
    }

    /**
     * 获取登录历史次数
     * @return
     */
    @APIMonitor(value = "login",parentAPI = "api")
    @GetMapping("/login")
    public List<Integer> getLoginCount(){
        return APIInvokeCount.LoginCount;
    }
}
