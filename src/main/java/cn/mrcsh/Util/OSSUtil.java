package cn.mrcsh.Util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class OSSUtil {
    @Value("${oss.alidomain}")
    private String ALI_DOMAIN;
    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.accesskeyid}")
    private String accessKeyId;
    @Value("${oss.accesskeysecret}")
    private String accessKeySecret;

    public String uploadImage(MultipartFile file) throws IOException {
        // 生成文件名
        String originalFilename = file.getOriginalFilename();
        String ext = "." + FilenameUtils.getExtension(originalFilename); // 后缀
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String fileName = uuid + ext;
        // 地域节点
        // OSS 客户端对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(
                "csh-test1",
                fileName,
                file.getInputStream()
        );
        ossClient.shutdown();
        return ALI_DOMAIN + fileName;
    }

    public void delete(String url) {
//        log.info(url);
        if (url != null && !url.equals("https://csh-test1.oss-cn-beijing.aliyuncs.com/user-fill.png")) {
            url = url.substring(url.indexOf("//") + 2);
            url = url.substring(url.indexOf("/") + 1);
//            log.info(url);
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            ossClient.deleteObject("csh-test1", url);
            ossClient.shutdown();
        }
    }

    public void test(){
        log.info(ALI_DOMAIN);
        log.info(endpoint);
        log.info(accessKeyId);
        log.info(accessKeySecret);
    }
}
