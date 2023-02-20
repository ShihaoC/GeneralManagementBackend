package cn.mrcsh.Entity.Api.amap;

import lombok.Data;

@Data
public class IPPositioning {
    private String status; // 返回结果状态值
    private String info; // 返回状态说明
    private String infocode; // 状态码
    private String province; // 省份名称
    private String city; // 城市名称
    private String adcode; // 城市的adcode编码
    private String rectangle; // 所在城市矩形区域范围
}
