package cn.mrcsh.Task;

import cn.mrcsh.Cache.APIInvokeCount;
import com.google.protobuf.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApiCountTask implements CommandLineRunner {
    @Override
    public void run(String... args) {
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(1000*60*2);
                    remove();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void remove(){
        if(APIInvokeCount.counts.size() == 15){
            APIInvokeCount.counts.remove(0);
            APIInvokeCount.counts.add(APIInvokeCount.AllCount);
            APIInvokeCount.LoginCount.remove(0);
            APIInvokeCount.LoginCount.add(APIInvokeCount.AllLoginCount);
            APIInvokeCount.AllCount = 0;
            APIInvokeCount.AllLoginCount = 0;

        }else {
            APIInvokeCount.counts.add(APIInvokeCount.AllCount);
            APIInvokeCount.counts.add(APIInvokeCount.AllLoginCount);
            APIInvokeCount.AllCount = 0;
            APIInvokeCount.AllLoginCount = 0;
        }
    }
}
