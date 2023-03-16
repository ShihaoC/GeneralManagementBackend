package cn.mrcsh.Mapper;

import cn.mrcsh.Entity.ELog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface ELogMapper extends BaseMapper<ELog> {
    int clear();
}
