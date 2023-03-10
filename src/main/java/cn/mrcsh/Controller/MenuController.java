package cn.mrcsh.Controller;

import cn.mrcsh.Entity.Menu;
import cn.mrcsh.Entity.MenuNode;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Service.Impl.MenuServiceImpl;
import cn.mrcsh.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuServiceImpl service;

    @GetMapping("/menus/{role_id}")
    public Object menus(@PathVariable int role_id){
        List<MenuNode<Menu>> menuNodes = service.roleMenus(role_id);
        return Result.success(menuNodes);
    }

    @GetMapping("/allMenus")
    public Object AllMenus(){
        List<MenuNode<Menu>> menuNodes = service.AllMenus();
        return Result.success(menuNodes);
    }
}
