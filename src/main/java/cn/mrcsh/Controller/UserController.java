package cn.mrcsh.Controller;

import cn.mrcsh.Entity.User;
import cn.mrcsh.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/user_query")
    public Object user_query(String search,int page){
        return service.selectList(search, page);
    }

    @GetMapping("/user_delete")
    public Object delete(int id){
        return service.delete(id);
    }
    @PostMapping("/user_update")
    public Object update(@RequestBody User user){
        return service.update(user);
    }
    @PostMapping("/user_insert")
    public Object insert(@RequestBody User user){
        return service.insert(user);
    }
}
