package cn.mrcsh.Service.Impl;

import cn.mrcsh.Entity.Department;
import cn.mrcsh.Entity.Factory.PagesFactory;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Mapper.DepartmentMapper;
import cn.mrcsh.Service.DepartmentService;
import cn.mrcsh.Util.PoiUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper mapper;

    @Override
    public Result insert(Department department) {
        int result = mapper.insert(Objects.requireNonNull(department));
        return Result.success(result);
    }

    @Override
    public Result delete(int id) {
        int result = 0;
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper
                .eq("id",id);
        try {
            result = mapper.delete(wrapper);
        } catch (Exception e) {
            return Result.fail("删除失败");
        }
        if (result > 0) {
            return Result.success(result);
        }
        return Result.fail("不存在此岗位");
    }

    @Override
    public Result update(Department department) {
        int result = 0;
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper
                .eq("id", department.getId());
        try {
             result = mapper.update(department, wrapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success(result);
    }

    @Override
    public Result selectList(int page) {
        List<Department> departments = null;
        Page<Department> departmentPage = null;
        try {
            departmentPage = new Page<>(page, 10);
            mapper.selectPage(departmentPage, null);
            departments = departmentPage.getRecords();
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
        return Result.success(new PagesFactory<>().getInstance(departmentPage.getCurrent(), departmentPage.getTotal(), departments));
    }

    @Override
    public Result selectAll() {
        List<Department> departments = null;
        try {
            departments = mapper.selectList(null);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("查询失败");
        }
        return Result.success(departments);
    }

    @Override
    public Result selectLikeSomething(int page, String query) {
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
            return Result.fail("查询出错");
        }
        return Result.success(new PagesFactory<>().getInstance(departmentPage.getCurrent(), departmentPage.getTotal(), departments));
    }

    @Override
    public int batch_Delete(List<Department> departments) {
        List<Integer> collect = departments.stream().map(Department::getId).collect(Collectors.toList());
        return mapper.deleteBatchIds(collect);
    }

    @Override
    public void export(HttpServletResponse response) {
        PoiUtil.export_Excel(response,mapper,Department.class);
    }
}
