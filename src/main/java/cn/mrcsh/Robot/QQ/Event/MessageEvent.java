package cn.mrcsh.Robot.QQ.Event;

import cn.mrcsh.Entity.Employee;
import cn.mrcsh.Service.Impl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import love.forte.simboot.annotation.Filter;
import love.forte.simboot.annotation.Listener;
import love.forte.simbot.LongID;
import love.forte.simbot.component.mirai.event.MiraiGroupMessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class MessageEvent {

    @Autowired
    private EmployeeServiceImpl service;

    @Listener
    @Filter(targets = @Filter.Targets(groups = {"622699505"}))
    public void onMessage(MiraiGroupMessageEvent event) {
        String context = event.getMessageContent().getPlainText();
        String s = context.replaceAll("#", " ");
        String[] args = s.split(" ");
        if (args.length == 1) {
            switch (args[0]) {
                case "签到":
                    LongID id = event.getAuthor().getId();
                    Employee simple = service.getSimpleByQQ(id.toString());
                    if (simple == null) {
                        event.sendBlocking("请先绑定qq号,输入\n#bind 姓名 手机号");
                    }
                    break;
            }
        }else if(args.length == 2){

        }else if(args.length == 3){
            switch (args[0]){
                case "bind":
                    log.info(args[1]);
                    log.info(args[2]);
                    break;
            }
        }else {
            log.info(context);
        }

    }

    @Listener
    public void onGroupMessage(MiraiGroupMessageEvent event){
        String context = event.getMessageContent().getPlainText();
        log.info(context);
    }

}
