package cn.mrcsh.Service.Impl;

import cn.mrcsh.Entity.ELog;
import cn.mrcsh.Entity.Factory.Pages;
import cn.mrcsh.Entity.Factory.PagesFactory;
import cn.mrcsh.Mapper.ELogMapper;
import cn.mrcsh.Service.ELogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ELogServiceImpl implements ELogService {

    @Autowired
    private ELogMapper mapper;

    @Override
    public int insert(ELog log) {
        return mapper.insert(log);
    }

    @Override
    public Pages<Object> query(String module, int page) {
        Page<ELog> logPage = new Page<>(page,10);
        mapper.selectPage(logPage,new QueryWrapper<ELog>().like("module",module));
        return new PagesFactory<>().getInstance(logPage.getCurrent(),logPage.getTotal(),logPage.getRecords());
    }

    @Override
    public int delete(List<ELog> logs) {
        List<Long> logIds = logs.stream().map(ELog::getId).collect(Collectors.toList());
        return mapper.deleteBatchIds(logIds);
    }

    @Override
    public int update(ELog log) {
        return mapper.update(log, new QueryWrapper<ELog>().eq("id", log.getId()));
    }
}
