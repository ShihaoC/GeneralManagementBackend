package cn.mrcsh.Util;

import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;

public class MD5Util {
    private MD5Util() {
    }

    @NotNull
    public static String md5Hex(String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * 生成MD5加密值
     * <hr/>
     * 加密算法 $MD5$+md5(md5+md5)
     *
     * @param str 需要加密的值
     * @return MD5值
     */
    @NotNull
    public static String generateMd5Hex(String str) {
        return "$MD5$" + md5Hex(md5Hex(str) + md5Hex(str));
    }


}
