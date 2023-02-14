package cn.mrcsh.Service.Impl;

import cn.mrcsh.Code.ResponseCode;
import cn.mrcsh.Entity.Employee;
import cn.mrcsh.Entity.Factory.PagesFactory;
import cn.mrcsh.Entity.Factory.Response;
import cn.mrcsh.Entity.Factory.ResponseFactory;
import cn.mrcsh.Mapper.EmployeeMapper;
import cn.mrcsh.Service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper mapper;

    @Override
    public Response<Object> insert(Employee employee) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("name", employee.getName());
        Employee checkExists = mapper.selectOne(wrapper);
        if (checkExists != null) {
            return new ResponseFactory<>().getInstance("已经存在", "已经存在", ResponseCode.IS_EXISTS);
        }
        int insert = 0;
        try {
            insert = mapper.insert(employee);
        } catch (Exception e) {
            return new ResponseFactory<>().getInstance(e.getMessage(), "添加失败", ResponseCode.ERROR);
        }
        return new ResponseFactory<>().getInstance(insert, "添加成功", ResponseCode.SUCCESS);
    }

    @Override
    public Response<Object> delete(int id,String name) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper
                .eq("name", name)
                .eq("id",id);
        int delete = 0;
        try {
            delete = mapper.delete(wrapper);
        } catch (Exception e) {

        }
        if(delete > 0){
            return new ResponseFactory<>().getInstance(delete,"删除成功",ResponseCode.NOT_EXISTS);
        }
        return new ResponseFactory<>().getInstance("没有这个人","删除失败",ResponseCode.NOT_EXISTS);

    }

    @Override
    public Response<Object> update(Employee employee) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper
                .eq("id",employee.getId())
                .eq("name", employee.getName());
        int update;
        try {
            update = mapper.update(employee, wrapper);
        } catch (Exception e) {
            return new ResponseFactory<>().getInstance(e.getMessage(),"修改失败",ResponseCode.ERROR);
        }
        if(update > 0){
            return new ResponseFactory<>().getInstance(update,"修改成功",ResponseCode.SUCCESS);
        }
        return new ResponseFactory<>().getInstance(update,"修改失败，没有这个人",ResponseCode.ERROR);
    }

    @Override
    public Response<Object> selectList(int page) {
        Page<Employee> employeePage = new Page<>(page,10);
        try {
            mapper.selectPage(employeePage,null);
        } catch (Exception e) {
            return new ResponseFactory<>().getInstance(e.getMessage(),"错误",ResponseCode.ERROR);
        }
        return new ResponseFactory<>().getInstance(new PagesFactory<>().getInstance(employeePage.getCurrent(),employeePage.getTotal(),employeePage.getRecords()),"查询成功",ResponseCode.SUCCESS);
    }

    @Override
    public Response<Object> selectLikeSomething(int page, String query) {
        Page<Employee> employeePage = new Page<>(page,10);
        try {
            QueryWrapper<Employee> wrapper = new QueryWrapper<>();
            wrapper
                    .like("name",query);
            mapper.selectPage(employeePage,wrapper);
        } catch (Exception e) {
            return new ResponseFactory<>().getInstance(e.getMessage(),"错误",ResponseCode.ERROR);
        }
        return new ResponseFactory<>().getInstance(new PagesFactory<>().getInstance(employeePage.getCurrent(),employeePage.getTotal(),employeePage.getRecords()),"查询成功",ResponseCode.SUCCESS);
    }
}
