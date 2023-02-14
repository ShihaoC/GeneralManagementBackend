package cn.mrcsh.Service;

import cn.mrcsh.Entity.Factory.Response;
import cn.mrcsh.Entity.User;

public interface UserService {
    Response<User> login(User user);

    Response<String> register(User user);
}
