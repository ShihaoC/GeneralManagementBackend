package cn.mrcsh.Service.Impl;

import cn.mrcsh.Entity.Factory.PagesFactory;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Entity.Role;
import cn.mrcsh.Mapper.RoleMapper;
import cn.mrcsh.Service.RoleService;
import cn.mrcsh.Util.PoiUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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

    @Override
    public int insert(Role role) {
        return mapper.insert(role);
    }

    @Override
    public int delete(int role_id) {
        return mapper.delete(new QueryWrapper<Role>().eq("id", role_id));
    }

    @Override
    public int update(Role role) {
        return mapper.update(role, new QueryWrapper<Role>().eq("id", role.getId()));
    }

    @Override
    public int updateStatue(int role_id, boolean statue) {
        Role role = mapper.selectOne(new QueryWrapper<Role>().eq("id", role_id));
        if(role != null){
            role.setEnable(statue);
        }else {
            return 0;
        }
        return mapper.update(role, new QueryWrapper<Role>().eq("id", role_id));
    }

    @Override
    public void export_excel(HttpServletResponse response) {
        PoiUtil.export_Excel(response,mapper,Role.class);
    }

    @Override
    public int batch_Delete(List<Role> list) {
        AtomicReference<Role> role = new AtomicReference<>();
        list.forEach(e->{
            if(e.getRole_name().equals("admin")){
                role.set(e);
            }
        });
        list.remove(role.get());
        List<Integer> role_ids = list.stream().map(Role::getId).collect(Collectors.toList());
        return mapper.deleteBatchIds(role_ids);
    }

    @Override
    public List<Role> selectAll() {
        return mapper.selectList(null);
    }
}
