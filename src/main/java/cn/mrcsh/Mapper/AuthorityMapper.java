package cn.mrcsh.Mapper;

import cn.mrcsh.Entity.Authority;
import cn.mrcsh.Entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AuthorityMapper extends BaseMapper<Authority> {
    List<Menu> selectByTypeAuthorities(@Param("role_id")int role_id);

    Integer selectId(@Param("id")int id);
}
