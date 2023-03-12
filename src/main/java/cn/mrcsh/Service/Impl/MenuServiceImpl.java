package cn.mrcsh.Service.Impl;

import cn.mrcsh.Entity.Authority;
import cn.mrcsh.Entity.Menu;
import cn.mrcsh.Entity.TreeNode;
import cn.mrcsh.Mapper.AuthorityMapper;
import cn.mrcsh.Mapper.MenuMapper;
import cn.mrcsh.Service.MenuService;
import cn.mrcsh.Util.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private AuthorityMapper mapper;


    public List<Menu> menus(){
        return menuMapper.selectList(null);
    }

    public List<TreeNode> roleMenus(int role_id){
        List<Menu> menus = mapper.selectByTypeAuthorities(role_id);
        List<TreeNode> list = new ArrayList<>();
        for (Menu menu : menus) {
            list.add(new TreeNode(menu.getId(), menu.getLevel(), menu.getName(), menu.isEnable(), menu));
        }
        TreeUtil treeUtil = new TreeUtil(list);
        List<TreeNode> treeNodes1 = treeUtil.buildTree();
        return treeNodes1;
    }

    @Override
    public List<TreeNode> AllMenus() {

        return null;
    }

    @Override
    public List<TreeNode<Menu>> AllParentNodes() {
        List<Menu> menus = menuMapper.selectAllParnetNodes();
        log.info(Arrays.toString(menus.toArray()));
        List<TreeNode<Menu>> nodes = new ArrayList<>();
        for (Menu menu : menus) {
            nodes.add(new TreeNode<Menu>(menu.getId(),menu.getLevel(),menu.getName(),menu.isEnable(),menu));
        }
        List<TreeNode<Menu>> list = new TreeUtil<Menu>(nodes).buildTree();
        return list;
    }

}
