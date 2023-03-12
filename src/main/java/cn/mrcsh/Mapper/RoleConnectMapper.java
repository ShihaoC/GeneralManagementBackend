package cn.mrcsh.Mapper;

import cn.mrcsh.Entity.Authority;
import cn.mrcsh.Entity.RoleConnect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleConnectMapper extends BaseMapper<RoleConnect> {
    List<Authority> list(@Param("role_id")int role_id);

    int deleteByAuthority_id(@Param("auth_id")int auth_id);

}
