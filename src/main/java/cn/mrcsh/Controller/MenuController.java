package cn.mrcsh.Controller;

import cn.hutool.core.lang.tree.Tree;
import cn.mrcsh.Annotations.Log;
import cn.mrcsh.Entity.*;
import cn.mrcsh.Mapper.AuthorityMapper;
import cn.mrcsh.Service.Impl.AuthorityService;
import cn.mrcsh.Service.Impl.MenuServiceImpl;
import cn.mrcsh.Service.MenuService;
import cn.mrcsh.Util.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
@Slf4j
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
    public Object allMenus(){
        List<Authority> authorities = mapper.selectList(null);
        List<TreeNode> list = new ArrayList<>();
        for (Authority authority : authorities) {
            list.add(new TreeNode(authority.getId(), authority.getLevel(), authority.getName(), authority.isEnable(), authority));
        }
        TreeUtil treeUtil = new TreeUtil(list);
        List<TreeNode> treeNodes1 = treeUtil.buildTree();
        return Result.success(treeNodes1);
    }

    @GetMapping("/allParentNodes")
    public Object allParentNodes(){
        List<TreeNode<Menu>> treeNodes = service.AllParentNodes();
        return Result.success(treeNodes);
    }

    @PostMapping("/insert/{Exclusions}")
    public Object insert(@RequestBody Authority authority,@PathVariable boolean Exclusions){
        if(authority.getLevel() == null){
            authority.setLevel(0);
        }
        authority.setId(null);
        authority.setExclusions(Exclusions);
        authority.setType("1");
        int insert = authorityService.insert(authority);
        return Result.success(insert);
    }

    @GetMapping("/del/{id}")
    @Log(module = "菜单模块",api = "删除菜单")
    public Object delete(@PathVariable int id){
        int result = authorityService.delete(id);
        return Result.success(result);
    }
}
