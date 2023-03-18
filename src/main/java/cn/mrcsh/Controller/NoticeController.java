package cn.mrcsh.Controller;


import cn.mrcsh.Entity.Notice;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Service.Impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 通知公告接口
 */
@RestController
@RequestMapping("/Notice")
public class NoticeController {

    @Autowired
    NoticeServiceImpl service;


    @PostMapping("/insert")
    public Object  insert( Notice notice){
        return Result.success(service.insert(notice));
    }

    @GetMapping("/delete")
    public Object delete(long id){
         service.delete(id);
         return Result.success("");
    }


    @GetMapping("/query")
    public Object query(long id){
        return Result.success(""+service.query(id));
    }

    @PostMapping("/update")
    public Object update(Notice notice){
        service.update(notice);
        return Result.success("");
    }



}
