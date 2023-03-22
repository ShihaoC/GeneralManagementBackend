package cn.mrcsh.Service.Impl;

import cn.mrcsh.Entity.Notice;
import cn.mrcsh.Mapper.NoticeMapper;
import cn.mrcsh.Service.NoticeService;
import cn.mrcsh.Util.RedisUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.val;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * wangwensen
 * 2023/3/18-14:21
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    RedisUtil redisUtil;

    @Autowired
    private NoticeMapper mapper;

    public void initalization(){
        bench_delete(queryAll());
        List<Notice> notices = mapper.selectList(null);
        notices.forEach(notice -> {
            redisUtil.set("notice:"+notice.getId(),JSON.toJSONString(notice));
        });
    }

    @Override
    public boolean insert(Notice notice) {
        long currentTimeMillis = System.currentTimeMillis();
        notice.setId(currentTimeMillis);
        notice.setCdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(notice.getTimeout() != -1){
            boolean set = redisUtil.set("notice:" + currentTimeMillis, JSON.toJSONString(notice), notice.getTimeout());
            return set;
        }else {
            boolean set = redisUtil.set("notice:" + currentTimeMillis, JSON.toJSONString(notice), notice.getTimeout());
            int insert = mapper.insert(notice);
            return insert > 0;
        }

    }

    @Override
    public void delete(long id) {
        redisUtil.del("notice:"+id);
        mapper.delete(new QueryWrapper<Notice>().eq("id",id));
    }

    @Override
    public void update(Notice notice) {
        redisUtil.del("notice:"+notice.getId());
        redisUtil.set("notice:"+notice.getId(), JSON.toJSONString(notice),notice.getTimeout());
        if(notice.getTimeout() != -1){
            mapper.delete(new QueryWrapper<Notice>().eq("id",notice.getId()));
        }else {
            Notice notice1 = mapper.selectOne(new QueryWrapper<Notice>().eq("id", notice.getId()));
            if(notice1 == null){
                mapper.insert(notice);
            }else {
                mapper.update(notice,new QueryWrapper<Notice>().eq("id",notice.getId()));
            }
        }

    }

    @Override
    public int updateStatue(int id, boolean statue) {
        return 0;
    }

    @Override
    public Object query(long id) {
        return JSON.parseObject(String.valueOf(redisUtil.get("notice:"+id)),Notice.class);
    }

    @Override
    public List<Notice> queryAll() {
        Set keys = redisUtil.keys("notice:*");

        List<Notice> notices = new ArrayList<>();
        for (Object key : keys) {
            Notice notice = JSON.parseObject(String.valueOf(redisUtil.get(String.valueOf(key))), Notice.class);
            notice.setTimeout(redisUtil.getExpire(String.valueOf(key)));
            notices.add(notice);
        }
        return notices;
    }

    @Override
    public boolean bench_delete(List<Notice> notices) {
        List<Long> Notice_Ids = notices.stream().map(Notice::getId).collect(Collectors.toList());
        Notice_Ids.forEach(id-> redisUtil.del("notice:"+id));
        return true;
    }
}
