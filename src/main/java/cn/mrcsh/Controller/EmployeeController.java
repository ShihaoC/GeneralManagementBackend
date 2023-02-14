package cn.mrcsh.Controller;

import cn.mrcsh.Entity.Employee;
import cn.mrcsh.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

/**
 * 员工管理接口
 */
@RestController
@RequestMapping("/em")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    /**
     * 创建员工信息
     * @param employee 员工对象
     * @return
     */
    @PostMapping(value = "/insert_employee")
    public Object insertEmployee(@RequestBody Employee employee){
        employee.setJoin_date(new Date(System.currentTimeMillis()));
        employee.setClockin(false);
        employee.setWork_num(0);
        employee.setQuit(false);
        return service.insert(employee);
    }

    /**
     * 查询所有员工信息
     * @param page 页数
     * @return
     */
    @GetMapping(value = "select_all")
    public Object selectAll(int page){
        return service.selectList(page);
    }

    /**
     * 根据姓名模糊查询员工信息
     * @param query 员工姓名
     * @param page 页数
     * @return
     */
    @GetMapping(value = "select_something")
    public Object selectSomething(String query,int page){
        return service.selectLikeSomething(page,query);
    }

    /**
     * 删除某个员工信息
     * @param id id
     * @param name 姓名
     * @return
     */
    @GetMapping(value = "delete_employee")
    public Object delete(int id,String name){
        return service.delete(id, name);
    }

    /**
     * 修改员工信息
     * @param employee 员工对象
     * @return
     */
    @PostMapping(value = "update_employee")
    public Object update(@RequestBody Employee employee){
        return service.update(employee);
    }

}
