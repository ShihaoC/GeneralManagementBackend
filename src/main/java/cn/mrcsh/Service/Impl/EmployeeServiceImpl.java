package cn.mrcsh.Service.Impl;

import cn.mrcsh.Code.ErrorCode;
import cn.mrcsh.Entity.Employee;
import cn.mrcsh.Entity.Factory.PagesFactory;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Mapper.EmployeeMapper;
import cn.mrcsh.Service.EmployeeService;
import cn.mrcsh.Util.PoiUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper mapper;

    @Override
    public Result insert(Employee employee) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper
                .eq("phone", employee.getPhone())
                .eq("name", employee.getName());
        Employee checkExists = mapper.selectOne(wrapper);
        if (checkExists != null) {
            return Result.fail("已经存在");
        }
        int insert = 0;
        try {
            insert = mapper.insert(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
        return Result.success(insert);
    }

    @Override
    public Result delete(int id, String name) {
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
            return Result.success(delete);
        }
        return Result.fail("没有这个人");

    }

    @Override
    public Result update(Employee employee) {
        int update;
        try {
            QueryWrapper<Employee> wrapper = new QueryWrapper<>();
            wrapper
                    .eq("id", employee.getId());
            update = mapper.update(employee, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("修改失败");
        }
        if (update > 0) {
            return Result.success(update);
        }
        return Result.fail("没有这个人");
    }

    @Override
    public Result selectList(int page) {
        Page<Employee> employeePage = new Page<>(page, 10);
        try {
            mapper.selectPage(employeePage, null);
        } catch (Exception e) {
            return Result.fail("错误");
        }
        return Result.success(new PagesFactory<>().getInstance(employeePage.getCurrent(), employeePage.getTotal(), employeePage.getRecords()));
    }

    @Override
    public Result selectLikeSomething(int page, String query) {
        Page<Employee> employeePage = new Page<>(page, 10);
        try {
            QueryWrapper<Employee> wrapper = new QueryWrapper<>();
            wrapper
                    .like("name", query);
            mapper.selectPage(employeePage, wrapper);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
        return Result.success(new PagesFactory<>().getInstance(employeePage.getCurrent(), employeePage.getTotal(), employeePage.getRecords()));
    }

    @Override
    @SneakyThrows
    public void export_Excel(HttpServletResponse response) {
        PoiUtil.export_Excel(response,mapper,Employee.class);
    }

    @Override
    public Result batch_Delete(List<Employee> list) {
        if(list.size() != 0){
            list.forEach((e)->{
                mapper.deleteById(e);
            });
        }
        return Result.success("成功");
    }

    @Override
    public Result import_excel(MultipartFile file) {
        return null;
    }

    @Override
    public Result update_statue(String statue, int em_id) {
        Employee employee = mapper.selectOne(new QueryWrapper<Employee>().eq("id", em_id));
        String message = "";
        if(statue.equals("签到")){
            if(!"已签到".equals(employee.getStatue()) && !"已经签退".equals(employee.getStatue())){
                employee.setStatue("已签到");
                message = "成功";
            }else {
                message = "已经签到";
            }

        }else if(statue.equals("签退")){
            if(!"已经签退".equals(employee.getStatue()) && "已签到".equals(employee.getStatue())){
                employee.setStatue("已经签退");
                message = "成功";
            }else {
                message = "已经签退或没有签到";
            }

        }

        mapper.update(employee, new QueryWrapper<Employee>().eq("id", em_id));
        return Result.success(message);
    }

    public Employee getSimpleByQQ(String qq){
        return mapper.selectOne(new QueryWrapper<Employee>().eq("bind_qq",qq));
    }
}
