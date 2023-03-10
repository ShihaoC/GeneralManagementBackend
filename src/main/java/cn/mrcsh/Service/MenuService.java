package cn.mrcsh.Service;

import cn.mrcsh.Entity.Menu;
import cn.mrcsh.Entity.MenuNode;

import java.util.List;

public interface MenuService {

    List<MenuNode<Menu>> roleMenus(int role_id);

    List<MenuNode<Menu>> AllMenus();
}
