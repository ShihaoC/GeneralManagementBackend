package cn.mrcsh.Service;


import cn.mrcsh.Entity.Employee;
import cn.mrcsh.Entity.Factory.Response;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface EmployeeService {
    Response<Object> insert(Employee employee);

    Response<Object> delete(int id,String name);

    Response<Object> update(Employee employee);

    Response<Object> selectList(int page);

    Response<Object> selectLikeSomething(int page,String query);

    void export_Excel(HttpServletResponse response);

    Response<Object> batch_Delete(List<Employee> list);

    Response<Object> import_excel(MultipartFile file);
}
