package cn.mrcsh.Service;

import cn.mrcsh.Entity.Department;
import cn.mrcsh.Entity.Result;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DepartmentService {
    Result insert(Department department);

    Result delete(int id);

    Result update(Department department);

    Result selectList(int page);

    Result selectAll();


    Result selectLikeSomething(int page,String query);

    int batch_Delete(List<Department> departments);

    void export(HttpServletResponse response);
}
