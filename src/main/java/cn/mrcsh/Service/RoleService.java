package cn.mrcsh.Service;

import cn.mrcsh.Entity.Result;

public interface RoleService {

    Result selectList(int page, String search);
}
