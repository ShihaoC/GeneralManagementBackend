package cn.mrcsh.Service;

import cn.mrcsh.Entity.Factory.Response;
import cn.mrcsh.Entity.User;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
    Response<User> login(User user);

    Response<String> register(User user);

    Response<Object> selectList(String search,int page);

    Response<Object> delete(int id);

    Response<Object> update(User user);

    Response<Object> insert(User user);

    Response<Object> update_statue(int id,boolean flag);

    void export_excel(HttpServletResponse response);
}
