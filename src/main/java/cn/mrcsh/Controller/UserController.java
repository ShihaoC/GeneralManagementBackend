package cn.mrcsh.Controller;

import cn.mrcsh.Entity.User;
import cn.mrcsh.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    /**
     * 查询用户
     * @param search 名字
     * @param page 页数
     * @return <T>
     */

    @GetMapping("/user_query")
    public Object user_query(String search,int page){
        return service.selectList(search, page);
    }

    /**
     * 根据id删除用户信息
     * @param id id
     * @return <T>
     */
    @GetMapping("/user_delete")
    public Object delete(int id){
        return service.delete(id);
    }

    /**
     * 修改员工信息
     * @param user 用户对象
     * @return <T>
     */
    @PostMapping("/user_update")
    public Object update(@RequestBody User user){
        return service.update(user);
    }

    /**
     * 添加用户信息
     * @param user 用户对象
     * @return <T>
     */
    @PostMapping("/user_insert")
    public Object insert(@RequestBody User user){
        return service.insert(user);
    }

    /**
     * 修改用户状态
     * @param id id
     * @param flag 状态 true/false
     * @return <T>
     */
    @GetMapping("/user_update_statue")
    public Object update_statue(int id,Boolean flag){
        return service.update_statue(id,flag);
    }

    @GetMapping("/user_export_excel")
    public void export(HttpServletResponse response){
        service.export_excel(response);
    }
}
