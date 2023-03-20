package cn.mrcsh.Service;

import cn.mrcsh.Entity.Notice;
import cn.mrcsh.Entity.Role;

import java.util.List;

/**
 * wangwensen
 * 2023/3/18-14:21
 */
public interface NoticeService {

    boolean insert(Notice notice);

    void delete(long id);

    void update(Notice notice);

    int updateStatue(int id, boolean statue);

    Object query(long id);

    List<Notice> queryAll();
}
