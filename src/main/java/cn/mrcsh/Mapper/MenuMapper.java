package cn.mrcsh.Mapper;

import cn.mrcsh.Entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> selectAllParnetNodes();
}
