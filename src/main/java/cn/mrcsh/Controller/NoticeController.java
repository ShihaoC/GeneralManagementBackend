package cn.mrcsh.Controller;

import cn.mrcsh.Robot.DingTalk.DingTalkUtil;
import com.taobao.api.ApiException;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/DingTalk")
public class NoticeController {

    @GetMapping("/sendMessage")
    @SneakyThrows
    public String sendMessage(String context){
        DingTalkUtil.DingTalkMessageBuilder builder = new DingTalkUtil.DingTalkMessageBuilder();
        builder
                .initialization()
                .setText(context)
                .build()
                .sendMessage();
        return null;
    }
}
