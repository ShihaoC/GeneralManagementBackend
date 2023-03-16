package cn.mrcsh.Controller;

import cn.mrcsh.Entity.ELog;
import cn.mrcsh.Entity.Factory.Pages;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Service.ELogService;
import cn.mrcsh.Util.PoiUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 日志接口
 */
@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {

    @Autowired
    private ELogService service;

    /**
     * 获取日志
     * @param module 模块名称
     * @param page 页数
     * @return {code: 200, msg: 'success', data: {object}}
     */
    @GetMapping("/getLogs")
    @PreAuthorize("hasAuthority('system:log:query')")
    public Object getAllLogs(String module,int page){
        log.info(String.valueOf(page));
        Pages query = service.query(module, page);
        return Result.success(query);
    }

    /**
     * 批量删除日志
     * @param logs 日志集合
     * @return {code: 200, msg: 'success', data: {object}}
     */
    @PostMapping("/del")
    @PreAuthorize("hasAuthority('system:log:delete')")
    public Object del(@RequestBody List<ELog> logs){
        int result = service.delete(logs);
        return Result.success(result);
    }

    @GetMapping("/export_excel")
    @PreAuthorize("hasAuthority('system:log:export')")
    public void export_excel(HttpServletResponse response){
        service.export_excel(response);
    }

    @GetMapping("/clear")
    @PreAuthorize("hasAuthority('system:log:clear')")
    public Object clear(){
        int res = service.clear();
        return Result.success(res);
    }
}
