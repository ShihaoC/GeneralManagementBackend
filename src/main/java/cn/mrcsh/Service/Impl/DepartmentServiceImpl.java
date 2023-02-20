package cn.mrcsh.Service.Impl;

import cn.mrcsh.Code.ErrorCode;
import cn.mrcsh.Entity.Department;
import cn.mrcsh.Entity.Factory.PagesFactory;
import cn.mrcsh.Entity.Factory.Response;
import cn.mrcsh.Entity.Factory.ResponseFactory;
import cn.mrcsh.Mapper.DepartmentMapper;
import cn.mrcsh.Service.DepartmentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    // 数据库操作Mapper
    @Autowired
    private DepartmentMapper mapper;

    // 添加方法
    @Override
    public Response<Object> insert(Department department) {
        int result = mapper.insert(Objects.requireNonNull(department));
        return new ResponseFactory<>().getInstance(result, "添加成功", ErrorCode.SUCCESS);
    }

    @Override
    public Response<Object> delete(int id) {
        int result = 0;
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper
                .eq("id",id);
        try {
            result = mapper.delete(wrapper);
        } catch (Exception e) {
            return new ResponseFactory<>().getInstance(e.getMessage(), "删除失败", ErrorCode.ERROR);
        }
        if (result > 0) {
            return new ResponseFactory<>().getInstance(result, "删除成功", ErrorCode.SUCCESS);
        }
        return new ResponseFactory<>().getInstance(result, "不存在此岗位", ErrorCode.NOT_EXISTS);
    }

    @Override
    public Response<Object> update(Department department) {
        int result = 0;
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper
                .eq("id", department.getId());
        try {
             result = mapper.update(department, wrapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseFactory<>().getInstance(result,"修改成功", ErrorCode.SUCCESS);
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
            return new ResponseFactory<>().getInstance(e.getMessage(), "服务器出错", ErrorCode.ERROR);
        }
        return new ResponseFactory<>().getInstance(new PagesFactory<>().getInstance(departmentPage.getCurrent(), departmentPage.getTotal(), departments), "查询成功", ErrorCode.SUCCESS);
    }

    @Override
    public Response<Object> selectAll() {
        List<Department> departments = null;
        try {
            departments = mapper.selectList(null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseFactory<>().getInstance(e.getMessage(),"查询失败", ErrorCode.ERROR);
        }
        return new ResponseFactory<>().getInstance(departments,"查询成功", ErrorCode.SUCCESS);
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
            return new ResponseFactory<>().getInstance(e.getMessage(), "查询出错", ErrorCode.ERROR);
        }
        return new ResponseFactory<>().getInstance(new PagesFactory<>().getInstance(departmentPage.getCurrent(), departmentPage.getTotal(), departments), "查询成功", ErrorCode.SUCCESS);
    }
}
