package cn.mrcsh.Service.Impl;

import cn.mrcsh.Entity.Menu;
import cn.mrcsh.Entity.MenuConnect;
import cn.mrcsh.Entity.MenuNode;
import cn.mrcsh.Mapper.MenuConnectMapper;
import cn.mrcsh.Mapper.MenuMapper;
import cn.mrcsh.Service.MenuService;
import cn.mrcsh.Util.MenuNodeUtil;
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
    private MenuConnectMapper connectMapper;

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> menus(){
        return menuMapper.selectList(null);
    }

    public List<MenuNode<Menu>> roleMenus(int role_id){
        List<MenuConnect> menuConnects = connectMapper.selectList(new QueryWrapper<MenuConnect>().eq("role_id", role_id));
        List<Integer> menu_ids = menuConnects.stream().map(MenuConnect::getMenu_id).collect(Collectors.toList());
        log.info(Arrays.toString(menu_ids.toArray()));
        List<Menu> menus = menuMapper.selectList(new QueryWrapper<Menu>().in(true,"id", menu_ids.toArray()));
        log.info(Arrays.toString(menus.toArray()));
        List<MenuNode<Menu>> menuNodes = new ArrayList<>();
        for (Menu menu : menus) {
            menuNodes.add(new MenuNode<>(menu.getId(),menu.getParent_id(),menu.getName(),menu));
        }
        MenuNodeUtil<Menu> menuNodeUtil =new MenuNodeUtil<>(menuNodes);
        List<MenuNode<Menu>> menuNodes1 = menuNodeUtil.buildTree();
        return menuNodes1;
    }

    @Override
    public List<MenuNode<Menu>> AllMenus() {
        List<Menu> menus = menuMapper.selectList(null);
        List<MenuNode<Menu>> menuNodes = new ArrayList<>();
        for (Menu menu : menus) {
            menuNodes.add(new MenuNode<>(menu.getId(),menu.getParent_id(),menu.getName(),menu));
        }
        MenuNodeUtil<Menu> menuNodeUtil =new MenuNodeUtil<>(menuNodes);
        List<MenuNode<Menu>> mn = menuNodeUtil.buildTree();
        return mn;
    }

}
