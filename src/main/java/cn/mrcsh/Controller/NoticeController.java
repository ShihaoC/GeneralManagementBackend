package cn.mrcsh.Controller;


import cn.mrcsh.Entity.Notice;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Service.Impl.NoticeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * 通知公告接口
 */
@RestController
@RequestMapping("/Notice")
@Slf4j
public class NoticeController {

    @Autowired
    NoticeServiceImpl service;


    @PostMapping("/insert/{timeout}")
    public Object insert(@RequestBody Notice notice,@PathVariable Long timeout) {
        notice.setTimeout(timeout);
        log.info(notice.toString());
        return Result.success(service.insert(notice));
    }

    @GetMapping("/delete")
    public Object delete(long id) {
        service.delete(id);
        return Result.success("");
    }


    @GetMapping("/query")
    public Object query(long id) {
        return Result.success("" + service.query(id));
    }

    @PostMapping("/update")
    public Object update(@RequestBody Notice notice) {
        log.info(notice.toString());
        notice.setStatue(true);
        service.update(notice);
        return Result.success("");
    }

    @GetMapping("/getAllNotice")
    public Object getAllNotice(){
        List<Notice> notices = service.queryAll();
        return Result.success(notices);
    }

    @PostMapping("/bench_delete")
    public Object Bench_Delete(@RequestBody List<Notice> notices){
        log.info(Arrays.toString(notices.toArray()));
        boolean flag = service.bench_delete(notices);
        return Result.success(flag);
    }


}
