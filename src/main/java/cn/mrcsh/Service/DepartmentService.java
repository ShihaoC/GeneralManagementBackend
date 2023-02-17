package cn.mrcsh.Service;

import cn.mrcsh.Entity.Department;
import cn.mrcsh.Entity.Factory.Response;

public interface DepartmentService {
    Response<Object> insert(Department department);

    Response<Object> delete(Department department);

    Response<Object> update(Department department);

    Response<Object> selectList(int page);

    Response<Object> selectAll();


    Response<Object> selectLikeSomething(int page,String query);
}
