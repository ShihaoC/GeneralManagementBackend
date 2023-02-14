package cn.mrcsh.Service.Impl;

import cn.mrcsh.Code.ResponseCode;
import cn.mrcsh.Entity.Department;
import cn.mrcsh.Entity.Factory.PagesFactory;
import cn.mrcsh.Entity.Factory.Response;
import cn.mrcsh.Entity.Factory.ResponseFactory;
import cn.mrcsh.Mapper.DepartmentMapper;
import cn.mrcsh.Service.DepartmentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper mapper;

    @Override
    public Response<Object> insert(Department department) {
        int result = mapper.insert(Objects.requireNonNull(department));
        return new ResponseFactory<>().getInstance(result, "添加成功", ResponseCode.SUCCESS);
    }

    @Override
    public Response<Object> delete(Department department) {
        int result = 0;
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper
                .eq("department", department.getDepartment());
        try {
            result = mapper.delete(wrapper);
        } catch (Exception e) {
            return new ResponseFactory<>().getInstance(e.getMessage(), "删除失败", ResponseCode.ERROR);
        }
        if (result > 0) {
            return new ResponseFactory<>().getInstance(result, "删除成功", ResponseCode.SUCCESS);
        }
        return new ResponseFactory<>().getInstance(result, "不存在此岗位", ResponseCode.NOT_EXISTS);
    }

    @Override
    public Response<Object> update(Department department) {
        int result = 0;
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper
                .eq("id", department.getId())
                .eq("department", department.getDepartment());
        try {
             result = mapper.update(department, wrapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseFactory<>().getInstance(result,"修改成功",ResponseCode.SUCCESS);
    }

    @Override
    public Response<Object> selectList(int page) {
        List<Department> departments = null;
        Page<Department> departmentPage = null;
        try {
            departmentPage = new Page<>(page, 10);
            mapper.selectPage(departmentPage, null);
            departments = departmentPage.getRecords();
        } catch (Exception e) {
            return new ResponseFactory<>().getInstance(e.getMessage(), "服务器出错", ResponseCode.ERROR);
        }
        return new ResponseFactory<>().getInstance(new PagesFactory<>().getInstance(departmentPage.getCurrent(), departmentPage.getTotal(), departments), "查询成功", ResponseCode.SUCCESS);
    }

    @Override
    public Response<Object> selectLikeSomething(int page, String query) {
        List<Department> departments = null;
        Page<Department> departmentPage = null;
        try {
            departmentPage = new Page<>(page, 10);
            QueryWrapper<Department> wrapper = new QueryWrapper<>();
            wrapper
                    .like("department", query);
            mapper.selectPage(departmentPage, wrapper);
            departments = departmentPage.getRecords();
        } catch (Exception e) {
            return new ResponseFactory<>().getInstance(e.getMessage(), "查询出错", ResponseCode.ERROR);
        }
        return new ResponseFactory<>().getInstance(new PagesFactory<>().getInstance(departmentPage.getCurrent(), departmentPage.getTotal(), departments), "查询成功", ResponseCode.SUCCESS);
    }
}
