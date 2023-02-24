package cn.mrcsh.Service.Impl;

import cn.mrcsh.Entity.Factory.PagesFactory;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Entity.Role;
import cn.mrcsh.Mapper.RoleMapper;
import cn.mrcsh.Service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper mapper;
    @Override
    public Result selectList(int page, String search) {
        Page<Role> page1 = new Page<>(page,10);
        mapper.selectPage(page1,new QueryWrapper<Role>().like("role_name",search));

        return Result.success(new PagesFactory<>().getInstance(page1.getCurrent(),page1.getTotal(),page1.getRecords()));
    }
}
