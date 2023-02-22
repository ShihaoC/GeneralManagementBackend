package cn.mrcsh.Task;

import cn.mrcsh.Cache.APIInvokeCount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class ConsoleTask implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 15; i++) {
            APIInvokeCount.counts.add(0);
        }
        new Thread(()->{
            Scanner input = new Scanner(System.in);
            while (true) {
                switch (input.nextLine()) {
                    case "stop":
                    case "end":
                    case "quit":
                        log.warn("服务器关闭中。。。");
                        input.close();
                        System.exit(0);
                        break;
                    case "clear":
                        for (int i = 0; i < 10; i++) {
                            System.out.println("\n\n");
                        }
                        break;
                    default:
                        log.error("未知指令");
                        break;
                }
            }
        }).start();
    }
}
