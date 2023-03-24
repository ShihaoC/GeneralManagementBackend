package cn.mrcsh.Service.Impl;

import cn.hutool.http.HttpUtil;
import cn.mrcsh.Cache.APIConfig;
import cn.mrcsh.Entity.Api.amap.IPPositioning;
import cn.mrcsh.Entity.Api.qweather.weather;
import cn.mrcsh.Service.WeatherService;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {


    @Override
    public weather getWeather(HttpServletRequest request) {
        String ip = "";
        if(request.getHeader("x-forwarded-for") != null){
            ip = request.getHeader("x-forwarded-for");
        }else if(request.getHeader("Proxy-Client-IP") != null){
            ip = request.getHeader("Proxy-Client-IP");
        }else if(request.getHeader("WL-Proxy-Client-IP") != null){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }else if(request.getRemoteAddr() != null){
            ip = request.getRemoteAddr();
        }
        if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1") || ip.contains("172")){
            ip = null;
        }
//        log.warn(ip);
        String location = "";
        if (ip != null && !ip.equals("")) {
            location = HttpUtil.get(String.format(APIConfig.amapURL_IP, "&ip="+ip));
        } else {
            location = HttpUtil.get(String.format(APIConfig.amapURL_IP,""));
        }
        IPPositioning ipPositioning = JSON.parseObject(location, IPPositioning.class);
        String[] split = ipPositioning.getRectangle().replaceAll("\\;", ",").split(",");
        String local = null;
        try {
            local = split[0] + "," + split[3];
        } catch (Exception e) {
            // 获取经纬度异常 默认北京经纬度
            local = "116.01,40.21";
        }
        // 根据经纬度获取天气
        String weather = HttpUtil.get(String.format(APIConfig.qweatherURL_NOW, local));
        // 反序列化为Java Bean
        weather weather1 = JSON.parseObject(weather, weather.class);
        return weather1;
    }
}
