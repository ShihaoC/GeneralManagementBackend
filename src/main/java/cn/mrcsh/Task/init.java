package cn.mrcsh.Task;

import cn.mrcsh.Service.Impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class init implements CommandLineRunner {
    @Autowired
    private NoticeServiceImpl service;
    @Override
    public void run(String... args) throws Exception {
        service.initalization();
    }
}
