package cn.mrcsh.Controller;

import cn.mrcsh.Annotations.APIMonitor;
import cn.mrcsh.Entity.Department;
import cn.mrcsh.Service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 岗位接口
 */
@RestController
@CrossOrigin
@RequestMapping("/dep")
@Slf4j
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    /**
     * 获取所有岗位信息
     * @return <?>
     */
    @APIMonitor(value = "all_department",parentAPI = "dep")
    @GetMapping("/all_department")
    public Object deps(){
        return service.selectAll();
    }

    /**
     * 查询所有员工信息
     * @param page 页数
     * @return <?>
     */
    @APIMonitor(value = "all_department_page",parentAPI = "dep")
    @GetMapping("/all_department_page")
    public Object dep(int page){
        return service.selectList(page);
    }

    /**
     * 根据名字模糊查询岗位信息
     * @param page 页数
     * @param query 岗位名称
     * @return 通用返回体
     */
    @APIMonitor(value = "somedepartment",parentAPI = "dep")
    @GetMapping("/somedepartment")
    public Object selectLikeSomething(int page,String query){
        return service.selectLikeSomething(page, query);
    }

    /**
     * 删除岗位信息
     * @return 通用返回体
     */
    @APIMonitor(value = "delete_department",parentAPI = "dep")
    @GetMapping(value = "/delete_department")
    public Object deleteDepartment(int id){
        return service.delete(id);
    }

    /**
     * 修改岗位信息
     * @param department 岗位对象
     * @return 通用返回体
     */
    @APIMonitor(value = "update_department",parentAPI = "dep")
    @PostMapping(value = "/update_department")
    public Object updateDepartment(@RequestBody Department department){
        return service.update(department);
    }

    /**
     * 添加岗位信息
     * @param department 岗位对象
     * @return 通用返回体
     */
    @APIMonitor(value = "insert_department",parentAPI = "dep")
    @PostMapping(value = "/insert_department")
    public Object insertDepartment(@RequestBody Department department){
        return service.insert(department);
    }
}
