package cn.mrcsh.Entity.Api.amap;

import lombok.Data;

@Data
public class IPPositioning {
    /**
     * 返回结果状态值
     */
    private String status;
    /**
     * 返回状态说明
     */
    private String info;
    /**
     * 状态码
     */
    private String infocode;
    /**
     * 省份名称
     */
    private String province;
    /**
     * 城市名称
     */
    private String city;
    /**
     * 城市的adcode编码
     */
    private String adcode;
    /**
     * 所在城市矩形区域范围
     */
    private String rectangle;
}
