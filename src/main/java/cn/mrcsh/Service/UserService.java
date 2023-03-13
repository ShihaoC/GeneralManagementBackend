package cn.mrcsh.Service;

import cn.mrcsh.Entity.Result;
import cn.mrcsh.Entity.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
    User login(User user);

    Result register(User user);

    Result selectList(String search,int page);

    Result delete(int id);

    Result update(User user);

    Result insert(User user);

    Result update_statue(int id,boolean flag);

    void export_excel(HttpServletResponse response);

    User getSimple(int id);

    int batch_Delete(List<User> users);

}
