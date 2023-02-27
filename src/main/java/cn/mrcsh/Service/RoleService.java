package cn.mrcsh.Service;

import cn.mrcsh.Entity.Result;
import cn.mrcsh.Entity.Role;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface RoleService {

    Result selectList(int page, String search);
    int insert(Role role);
    int delete(int role_id);
    int update(Role role);
    int updateStatue(int role_id,boolean statue);
    void export_excel(HttpServletResponse response);

    int batch_Delete(List<Role> list);

}
