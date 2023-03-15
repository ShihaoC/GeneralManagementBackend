package cn.mrcsh;

import cn.mrcsh.Robot.DingTalk.DingTalkUtil;
import com.taobao.api.ApiException;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, ApiException {
        DingTalkUtil.DingTalkMessageBuilder builder = new DingTalkUtil.DingTalkMessageBuilder();
        builder
                .initialization()
                .setAt("王文森")
                .build()
                .sendMessage();

        String a = "a";


    }
}
