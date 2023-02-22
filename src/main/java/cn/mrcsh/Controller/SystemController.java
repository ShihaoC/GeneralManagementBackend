package cn.mrcsh.Controller;

import cn.mrcsh.Entity.CPU;
import cn.mrcsh.Entity.Memory;
import cn.mrcsh.Service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统信息接口
 */
@RestController
@CrossOrigin
@RequestMapping("/system")
public class SystemController {
    @Autowired
    SystemService service;

    /**
     * 获取CPU信息
     * @return CPU信息
     */
    @GetMapping("/cpu")
    public List<CPU> getCpus(){
        return service.getCPUInfo();
    }

    /**
     * 获取内存信息
     * @return 内存信息
     */
    @GetMapping("/mem")
    public List<Memory> getMem(){
        return service.getMemoryInfo();
    }
}
