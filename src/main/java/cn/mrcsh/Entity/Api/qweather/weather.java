package cn.mrcsh.Entity.Api.qweather;

import lombok.Data;

@Data
public class weather {
    private String code; //  API状态码
    private String updateTime; // 当前API的最近更新时间
    private String fxLink; // 当前数据的响应式页面，便于嵌入网站或应用
    private now now;
    @Data
    public class now{
        private String obsTime; // 数据观测时间
        private String temp; // 温度，默认单位：摄氏度
        private String feelsLike; // 体感温度，默认单位：摄氏度
        private String icon; // 体感温度，默认单位：摄氏度
        private String text; // 天气状况的文字描述，包括阴晴雨雪等天气状态的描述
        private String wind360; // 风向360角度
        private String windDir; // 风向
        private String windScale; // 风力等级
        private String windSpeed; // 风速，公里/小时
        private String humidity; // 相对湿度，百分比数值
        private String precip; // 当前小时累计降水量，默认单位：毫米
        private String pressure; // 大气压强，默认单位：百帕
        private String vis; // 能见度，默认单位：公里
        private String cloud; // 云量，百分比数值。可能为空
        private String dew; // 露点温度。可能为空
    }
}
