package cn.mrcsh.Task;

import cn.mrcsh.Cache.APIInvokeCount;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class LogTask implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            while (true) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info(String.valueOf(APIInvokeCount.AllCount));
            }
        }).start();
    }
}
