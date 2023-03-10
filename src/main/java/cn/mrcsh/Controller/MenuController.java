package cn.mrcsh.Controller;

import cn.hutool.core.lang.tree.Tree;
import cn.mrcsh.Entity.*;
import cn.mrcsh.Mapper.AuthorityMapper;
import cn.mrcsh.Service.Impl.AuthorityService;
import cn.mrcsh.Service.Impl.MenuServiceImpl;
import cn.mrcsh.Service.MenuService;
import cn.mrcsh.Util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuServiceImpl service;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private AuthorityMapper mapper;

    @GetMapping("/menus/{role_id}")
    public Object menus(@PathVariable int role_id){
        List<TreeNode> treeNodes = service.roleMenus(role_id);
        return Result.success(treeNodes);
    }

    @GetMapping("/allMenus")
    public Object test1(){
        List<Authority> authorities = mapper.selectList(null);
        List<TreeNode> list = new ArrayList<>();
        for (Authority authority : authorities) {
            list.add(new TreeNode(authority.getId(), authority.getLevel(), authority.getName(), authority.isEnable(), authority));
        }
        TreeUtil treeUtil = new TreeUtil(list);
        List<TreeNode> treeNodes1 = treeUtil.buildTree();
        return Result.success(treeNodes1);
    }
}
