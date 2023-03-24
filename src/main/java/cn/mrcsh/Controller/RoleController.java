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

/**
 * 角色接口
 */
@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService service;

    /**
     * 查询角色
     * @param page 页数
     * @param search 查询条件
     * @return DTO
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('system:role:query')")
    public Object list(int page, String search) {
        return service.selectList(page, search);
    }

    /**
     * 查询所有角色
     * @return DTO
     */
    @GetMapping("/all")
    public Object getAll() {
        List<Role> roles = service.selectAll();
        return Result.success(roles);
    }

    /**
     * 添加角色
     * @param role 角色Vo
     * @return DTO
     */
    @PostMapping("/insert")
    @PreAuthorize("hasAuthority('system:role:insert')")
    @Log(module = "角色模块",api = "创建角色")
    public Result insert(@RequestBody Role role) {
        int insert = service.insert(role);
        return Result.success(insert);
    }

    /**
     * 修改角色
     * @param role 角色VO
     * @return DTO
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('system:role:update')")
    @Log(module = "角色模块",api = "修改角色")
    public Result update(@RequestBody Role role) {
        int update = service.update(role);
        return Result.success(update);
    }

    /**
     * 删除角色
     * @param id 角色id
     * @return DTO
     */
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('system:role:delete')")
    @Log(module = "角色模块",api = "删除角色")
    public Result delete(@PathVariable int id) {
        int delete = service.delete(id);
        return Result.success(delete);
    }

    /**
     * 修改角色状态
     * @param id 角色id
     * @param statue 角色状态
     * @return DTO
     */
    @GetMapping("/update_statue/{id}")
    @PreAuthorize("hasAuthority('system:role:update')")
    @Log(module = "角色模块",api = "修改角色状态")
    public Result update_statue(@PathVariable int id, boolean statue) {
        int i = service.updateStatue(id, statue);
        return Result.success(i);
    }

    /**
     * 导出员工信息
     * @return DTO
     */
    @GetMapping("/export_excel")
    @PreAuthorize("hasAuthority('system:role:export')")
    @Log(module = "角色模块",api = "导出角色Excel")
    public Result export_excel(HttpServletResponse response) {
        service.export_excel(response);
        return Result.success("导出成功");
    }

    /**
     * 批量删除角色
     * @param list 角色ids
     * @return DTO
     */
    @PostMapping("/batch_Delete")
//    @Log(module = "角色模块",api = "批量删除角色")
    public Result batch_Delete(@RequestBody List<Role> list) {
        int i = service.batch_Delete(list);
        return Result.success(i);
    }
}
