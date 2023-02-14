package cn.mrcsh.Service;


import cn.mrcsh.Entity.Employee;
import cn.mrcsh.Entity.Factory.Response;

public interface EmployeeService {
    Response<Object> insert(Employee employee);

    Response<Object> delete(int id,String name);

    Response<Object> update(Employee employee);

    Response<Object> selectList(int page);

    Response<Object> selectLikeSomething(int page,String query);
}
