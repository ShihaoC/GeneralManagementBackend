package cn.mrcsh.Entity;

import lombok.Data;

@Data
public class Notice {
    /**
     * ID
     */
    private long id;
    /**
     * 内容
     */
    private String context;
    /**
     * 超时时间
     */
    private Long Timeout;
    /**
     * 类型
     */
    private String type;
    /**
     * 状态
     */
    private boolean statue;
}
