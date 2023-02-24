package cn.mrcsh.Service;


import cn.mrcsh.Entity.Employee;
import cn.mrcsh.Entity.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface EmployeeService {
    Result insert(Employee employee);

    Result delete(int id,String name);

    Result update(Employee employee);

    Result selectList(int page);

    Result selectLikeSomething(int page,String query);

    void export_Excel(HttpServletResponse response);

    Result batch_Delete(List<Employee> list);

    Result import_excel(MultipartFile file);
}
