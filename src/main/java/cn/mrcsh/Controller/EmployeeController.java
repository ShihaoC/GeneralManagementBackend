package cn.mrcsh.Controller;

import cn.mrcsh.Annotations.APIMonitor;
import cn.mrcsh.Annotations.Log;
import cn.mrcsh.Entity.Employee;
import cn.mrcsh.Service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

/**
 * 员工管理接口
 */
@RestController
@Slf4j
@RequestMapping("/em")
@CrossOrigin(origins = "*", exposedHeaders = "Access-Control-Allow-Origin")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    /**
     * 创建员工信息
     *
     * @param employee 员工对象
     * @return 通用返回体
     */
    @APIMonitor(api = "insert_employee", parentAPI = "employee")
    @PostMapping(value = "/insert_employee")
    @PreAuthorize("hasAuthority('system:employee:insert')")
    @Log(module = "员工模块",api = "添加员工")
    public Object insertEmployee(@RequestBody Employee employee) {
        employee.setJoin_date(new Date(System.currentTimeMillis()));
        employee.setClockin(false);
        employee.setWork_num(0);
        employee.setQuit(false);
        employee.setStatue("");
        return service.insert(employee);
    }

    /**
     * 查询所有员工信息
     *
     * @param page 页数
     * @return 通用返回体
     */
    @APIMonitor(api = "select_all", parentAPI = "employee")
    @GetMapping(value = "/select_all")
    @PreAuthorize("hasAuthority('system:employee:query')")
    public Object selectAll(int page) {
        return service.selectList(page);
    }

    /**
     * 根据姓名模糊查询员工信息
     *
     * @param query 员工姓名
     * @param page  页数
     * @return 通用返回体
     */
    @APIMonitor(api = "select_something", parentAPI = "employee")
    @GetMapping(value = "/select_something")
    @PreAuthorize("hasAuthority('system:employee:query')")
    public Object selectSomething(String query, int page) {
        return service.selectLikeSomething(page, query);
    }

    /**
     * 删除某个员工信息
     *
     * @param id   id
     * @param name 姓名
     * @return 通用返回体
     */
    @APIMonitor(api = "delete_employee", parentAPI = "employee")
    @GetMapping(value = "/delete_employee")
    @PreAuthorize("hasAuthority('system:employee:delete')")
    @Log(module = "员工模块",api = "删除员工")
    public Object delete(int id, String name) {
        return service.delete(id, name);
    }

    /**
     * 修改员工信息
     *
     * @param employee 员工对象
     * @return 通用返回体
     */
    @APIMonitor(api = "update_employee", parentAPI = "employee")
    @PostMapping(value = "/update_employee")
    @PreAuthorize("hasAuthority('system:employee:update')")
    @Log(module = "员工模块",api = "修改员工")
    public Object update(@RequestBody Employee employee) {
        return service.update(employee);
    }

    /**
     * 导出EXCEL
     *
     * @param response
     */
    @APIMonitor(api = "export_excel", parentAPI = "employee")
    @GetMapping(value = "/export_excel")
    @PreAuthorize("hasAuthority('system:employee:export')")
    @Log(module = "员工模块",api = "导出员工Excel")
    public void export(HttpServletResponse response) {
        service.export_Excel(response);
    }

    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    @APIMonitor(api = "batch_Delete", parentAPI = "employee")
    @PostMapping(value = "/batch_Delete")
    @PreAuthorize("hasAuthority('system:employee:betch')")
    @Log(module = "员工模块",api = "批量删除员工")
    public Object batch_Delete(@RequestBody List<Employee> list) {
        return service.batch_Delete(list);
    }

    @Log(module = "员工模块",api = "签到")
    @GetMapping("/login")
    public Object login(String statue, int em_id) {
        return service.update_statue(statue, em_id);
    }
}
