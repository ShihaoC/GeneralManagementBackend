package cn.mrcsh.Controller;

import cn.mrcsh.Annotations.Log;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Entity.Role;
import cn.mrcsh.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService service;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('system:role:query')")
    public Object list(int page, String search) {
        return service.selectList(page, search);
    }

    @GetMapping("/all")
    public Object getAll() {
        List<Role> roles = service.selectAll();
        return Result.success(roles);
    }

    @PostMapping("/insert")
    @PreAuthorize("hasAuthority('system:role:insert')")
    @Log(module = "角色模块",api = "创建角色")
    public Result insert(@RequestBody Role role) {
        int insert = service.insert(role);
        return Result.success(insert);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('system:role:update')")
    @Log(module = "角色模块",api = "修改角色")
    public Result update(@RequestBody Role role) {
        int update = service.update(role);
        return Result.success(update);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('system:role:delete')")
    @Log(module = "角色模块",api = "删除角色")
    public Result delete(@PathVariable int id) {
        int delete = service.delete(id);
        return Result.success(delete);
    }

    @GetMapping("/update_statue/{id}")
    @PreAuthorize("hasAuthority('system:role:update')")
    @Log(module = "角色模块",api = "修改角色状态")
    public Result update_statue(@PathVariable int id, boolean statue) {
        int i = service.updateStatue(id, statue);
        return Result.success(i);
    }

    @GetMapping("/export_excel")
    @PreAuthorize("hasAuthority('system:role:export')")
    @Log(module = "角色模块",api = "导出角色Excel")
    public Result export_excel(HttpServletResponse response) {
        service.export_excel(response);
        return Result.success("导出成功");
    }

    @PostMapping("/batch_Delete")
//    @Log(module = "角色模块",api = "批量删除角色")
    public Result batch_Delete(@RequestBody List<Role> list) {
        int i = service.batch_Delete(list);
        return Result.success(i);
    }
}
