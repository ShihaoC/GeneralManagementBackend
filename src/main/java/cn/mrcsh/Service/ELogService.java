package cn.mrcsh.Service;

import cn.mrcsh.Entity.ELog;
import cn.mrcsh.Entity.Factory.Pages;

import java.util.List;

public interface ELogService {

    int insert(ELog log);

    Pages query(String module, int page);

    int delete(List<ELog> logs);

    int update(ELog log);
}
