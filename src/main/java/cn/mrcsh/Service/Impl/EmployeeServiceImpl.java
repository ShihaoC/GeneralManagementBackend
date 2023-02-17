package cn.mrcsh.Service.Impl;

import cn.mrcsh.Annotation.ExcelFieldName;
import cn.mrcsh.Code.ErrorCode;
import cn.mrcsh.Entity.Employee;
import cn.mrcsh.Entity.Factory.PagesFactory;
import cn.mrcsh.Entity.Factory.Response;
import cn.mrcsh.Entity.Factory.ResponseFactory;
import cn.mrcsh.Mapper.EmployeeMapper;
import cn.mrcsh.Service.EmployeeService;
import cn.mrcsh.Util.PoiUtil;
import cn.mrcsh.Util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper mapper;

    @Override
    public Response<Object> insert(Employee employee) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper
                .eq("phone", employee.getPhone())
                .eq("name", employee.getName());
        Employee checkExists = mapper.selectOne(wrapper);
        if (checkExists != null) {
            return new ResponseFactory<>().getInstance("已经存在", "已经存在", ErrorCode.IS_EXISTS);
        }
        int insert = 0;
        try {
            insert = mapper.insert(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseFactory<>().getInstance(e.getMessage(), "添加失败", ErrorCode.ERROR);
        }
        return new ResponseFactory<>().getInstance(insert, "添加成功", ErrorCode.SUCCESS);
    }

    @Override
    public Response<Object> delete(int id, String name) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper
                .eq("name", name)
                .eq("id", id);
        int delete = 0;
        try {
            delete = mapper.delete(wrapper);
        } catch (Exception e) {

        }
        if (delete > 0) {
            return new ResponseFactory<>().getInstance(delete, "删除成功", ErrorCode.NOT_EXISTS);
        }
        return new ResponseFactory<>().getInstance("没有这个人", "删除失败", ErrorCode.NOT_EXISTS);

    }

    @Override
    public Response<Object> update(Employee employee) {
        int update;
        try {
            QueryWrapper<Employee> wrapper = new QueryWrapper<>();
            wrapper
                    .eq("id", employee.getId());
            update = mapper.update(employee, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseFactory<>().getInstance(e.getMessage(), "修改失败", ErrorCode.ERROR);
        }
        if (update > 0) {
            return new ResponseFactory<>().getInstance(update, "修改成功", ErrorCode.SUCCESS);
        }
        return new ResponseFactory<>().getInstance(update, "修改失败，没有这个人", ErrorCode.ERROR);
    }

    @Override
    public Response<Object> selectList(int page) {
        Page<Employee> employeePage = new Page<>(page, 10);
        try {
            mapper.selectPage(employeePage, null);
        } catch (Exception e) {
            return new ResponseFactory<>().getInstance(e.getMessage(), "错误", ErrorCode.ERROR);
        }
        return new ResponseFactory<>().getInstance(new PagesFactory<>().getInstance(employeePage.getCurrent(), employeePage.getTotal(), employeePage.getRecords()), "查询成功", ErrorCode.SUCCESS);
    }

    @Override
    public Response<Object> selectLikeSomething(int page, String query) {
        Page<Employee> employeePage = new Page<>(page, 10);
        try {
            QueryWrapper<Employee> wrapper = new QueryWrapper<>();
            wrapper
                    .like("name", query);
            mapper.selectPage(employeePage, wrapper);
        } catch (Exception e) {
            return new ResponseFactory<>().getInstance(e.getMessage(), "错误", ErrorCode.ERROR);
        }
        return new ResponseFactory<>().getInstance(new PagesFactory<>().getInstance(employeePage.getCurrent(), employeePage.getTotal(), employeePage.getRecords()), "查询成功", ErrorCode.SUCCESS);
    }

    @Override
    @SneakyThrows
    public void export_Excel(HttpServletResponse response) {
        List<String> FieldNames = new ArrayList<>();
        List<String> ExcelFieldNames = new ArrayList<>();
        ReflectUtil.forEachFields(Employee.class, (e) -> {
            FieldNames.add(e.getName());
            ExcelFieldNames.add(e.getAnnotation(ExcelFieldName.class).value());
        });
        List<Employee> employees = mapper.selectList(null);
        String[] Entity_FieldNames = FieldNames.toArray(new String[]{});
        String[] Excel_FieldNames = ExcelFieldNames.toArray(new String[]{});
        PoiUtil.start_download(response, UUID.randomUUID().toString(), employees, Excel_FieldNames, Entity_FieldNames);
    }
}
