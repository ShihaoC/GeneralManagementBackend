package cn.mrcsh.Controller;

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
    public Object list(int page,String search){
        return service.selectList(page, search);
    }

    @PostMapping("/insert")
    @PreAuthorize("hasAuthority('system:role:insert')")
    public Result insert(@RequestBody Role role){
        int insert = service.insert(role);
        return Result.success(insert);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('system:role:update')")
    public Result update(@RequestBody Role role){
        int update = service.update(role);
        return Result.success(update);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('system:role:delete')")
    public Result delete(@PathVariable int id){
        int delete = service.delete(id);
        return Result.success(delete);
    }

    @GetMapping("/update_statue/{id}")
    @PreAuthorize("hasAuthority('system:role:update')")
    public Result update_statue(@PathVariable int id,boolean statue){
        int i = service.updateStatue(id, statue);
        return Result.success(i);
    }

    @GetMapping("/export_excel")
    @PreAuthorize("hasAuthority('system:role:export')")
    public Result export_excel(HttpServletResponse response){
        service.export_excel(response);
        return Result.success("导出成功");
    }
    @PostMapping("/batch_Delete")
    public Result batch_Delete(@RequestBody List<Role> list){
        int i = service.batch_Delete(list);
        return Result.success(i);
    }
}
