package cn.mrcsh.Controller;

import cn.mrcsh.Annotations.Log;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Entity.User;
import cn.mrcsh.Service.UserService;
import cn.mrcsh.Util.OSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService service;

    /**
     * 查询用户
     *
     * @param search 名字
     * @param page   页数
     * @return <T>
     */

    @GetMapping("/user_query")
    @PreAuthorize("hasAuthority('system:user:query')")
    public Object user_query(String search, int page) {
        return service.selectList(search, page);
    }

    /**
     * 根据id删除用户信息
     *
     * @param id id
     * @return <T>
     */
    @GetMapping("/user_delete/{id}")
    @PreAuthorize("hasAuthority('system:user:delete')")
    @Log(module = "用户模块",api = "删除用户")
    public Object delete(@PathVariable int id) {
        return service.delete(id);
    }

    /**
     * 修改员工信息
     *
     * @param user 用户对象
     * @return <T>
     */
    @PostMapping("/user_update")
    @PreAuthorize("hasAuthority('system:user:update')")
    @Log(module = "用户模块",api = "修改用户")
    public Object update(@RequestBody User user) {
        return service.update(user);
    }

    /**
     * 添加用户信息
     *
     * @param user 用户对象
     * @return <T>
     */
    @PostMapping("/user_insert")
    @PreAuthorize("hasAuthority('system:user:insert')")
    @Log(module = "用户模块",api = "添加用户")
    public Object insert(@RequestBody User user) {
        return service.insert(user);
    }

    /**
     * 修改用户状态
     *
     * @param id     id
     * @param statue 状态 true/false
     * @return <T>
     */
    @GetMapping("/user_update_statue/{id}")
    @PreAuthorize("hasAuthority('system:user:update')")
    @Log(module = "用户模块",api = "修改用户状态")
    public Object update_statue(@PathVariable int id, boolean statue) {
        return service.update_statue(id, statue);
    }

    @GetMapping("/user_export_excel")
    @PreAuthorize("hasAuthority('system:user:export')")
    @Log(module = "用户模块",api = "导出用户Excel")
    public void export(HttpServletResponse response) {
        service.export_excel(response);
    }

    @PostMapping("/uploadImage/{id}")
    public Result upload(MultipartFile file, @PathVariable int id) throws IOException {
        String OSSUrl = OSSUtil.uploadImage(file);
        User simple = service.getSimple(id);
        OSSUtil.delete(simple.getImage_url());
        simple.setImage_url(OSSUrl);
        return service.update(simple);
    }

    @GetMapping("/headImage/{id}")
    public Result getHead(@PathVariable int id) {
        User simple = service.getSimple(id);
        return Result.success(simple.getImage_url());
    }

    @PostMapping("/batch_Delete")
    @PreAuthorize("hasAuthority('system:user:delete')")
    @Log(module = "用户模块",api = "批量删除用户")
    public Result batch_Delete(@RequestBody List<User> users) {
        int result = service.batch_Delete(users);
        return Result.success(result);
    }
}
