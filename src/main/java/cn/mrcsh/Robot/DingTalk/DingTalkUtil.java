package cn.mrcsh.Robot.DingTalk;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class DingTalkUtil {

    private static final String accessToken = "a2b720d5a4fa012ef401acacb84a4557a4f43ed50582e4f3ce6e9aed546dd021";

    private static final String autograph = "SEC4a028359d9dda6f053296002a7445b3280a90d1e926161a31d7f60cbd68a10bd";

    private OapiRobotSendRequest request;

    private DingTalkUtil(OapiRobotSendRequest request) {
        this.request = request;
    }

    /**
     * 获取加密后签名
     *
     * @param autograph 签名
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     */
    public String getSign(String autograph) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        Long timestamp = System.currentTimeMillis();
        String secret = autograph;

        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        return sign;
    }

    /**
     * 获取API地址
     *
     * @return URL
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public String getAPIUrl() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String Template = "https://oapi.dingtalk.com/robot/send?access_token=%s&timestamp=%s&sign=%s";
        return String.format(Template, accessToken, System.currentTimeMillis(), getSign(autograph));
    }

    public String sendMessage() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, ApiException {
        DingTalkClient client = new DefaultDingTalkClient(getAPIUrl());
        OapiRobotSendResponse execute = client.execute(request);
        return execute.getMessage();
    }

    public static class DingTalkMessageBuilder {
        private OapiRobotSendRequest request;

        public DingTalkMessageBuilder initialization(){
            request = new OapiRobotSendRequest();
            return this;
        }

        public DingTalkUtil build() {
            return new DingTalkUtil(request);
        }

        public DingTalkMessageBuilder setText(String Content) {
            request.setMsgtype("text");
            OapiRobotSendRequest.Text tx = new OapiRobotSendRequest.Text();
            tx.setContent(Content);
            request.setText(tx);
            return this;
        }

        public DingTalkMessageBuilder setLink(String url,String PicUrl, String Title, String Text) {
            request.setMsgtype("link");
            OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
            link.setMessageUrl(url);
            link.setPicUrl(PicUrl);
            link.setTitle(Title);
            link.setText(Text);
            request.setLink(link);
            return this;
        }

        public DingTalkMessageBuilder setMarkdown(String Title, String Text) {
            request.setMsgtype("markdown");
            OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
            markdown.setTitle(Title);
            markdown.setText(Text);
            request.setMarkdown(markdown);
            return this;
        }

        public DingTalkMessageBuilder setAt(String ... phones) {
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            at.setAtMobiles(Arrays.asList(phones));
            request.setAt(at);
            return this;
        }

        public DingTalkMessageBuilder setAt(boolean isAll) {
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            at.setIsAtAll(isAll);
            request.setAt(at);
            return this;
        }


    }
}
