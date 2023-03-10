package cn.mrcsh.Service;


import cn.mrcsh.Entity.TreeNode;

import java.util.List;

public interface MenuService {

    List<TreeNode> roleMenus(int role_id);

    List<TreeNode> AllMenus();
}
