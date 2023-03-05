package cn.mrcsh.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class SysNoticeController {

    @GetMapping("/getNotice")
    public Object getNotice(){
        return null;
    }

    @PostMapping("/addNotice")
    public Object addNotice(){
        return null;
    }

    @GetMapping("/getType")
    public Object getType(){
        return null;
    }
}
