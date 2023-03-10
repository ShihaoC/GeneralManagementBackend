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
            APIInvokeCount.LoginCount.add(0);
        }
        new Thread(()->{
            Scanner input = new Scanner(System.in);
            while (true) {
                System.out.print("EmployeeManagement > ");
                switch (input.nextLine()) {
                    case "stop":
                    case "end":
                    case "quit":
                        log.warn("server stoping...");
                        input.close();
                        System.exit(0);
                        break;
                    case "clear":
                        for (int i = 0; i < 10; i++) {
                            System.out.println("\n\n");
                        }
                        break;
                    case "":
                        break;
                    default:
                        log.error("unknow command");
                        break;
                }
            }
        }).start();
    }
}
