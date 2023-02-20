package cn.mrcsh.Service.Impl;

import cn.hutool.http.HttpUtil;
import cn.mrcsh.Cache.APIConfig;
import cn.mrcsh.Entity.Api.amap.IPPositioning;
import cn.mrcsh.Entity.Api.qweather.weather;
import cn.mrcsh.Service.WeatherService;
import com.alibaba.fastjson2.JSON;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
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
        if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){
            ip = null;
        }
        String location = "";
        if (ip != null && !ip.equals("")) {
            location = HttpUtil.get(String.format(APIConfig.amapURL_IP, "&ip="+ip));
        } else {
            location = HttpUtil.get(String.format(APIConfig.amapURL_IP,""));
        }
        System.out.println(location);
        System.out.println(ip);
        IPPositioning ipPositioning = JSON.parseObject(location, IPPositioning.class);
        String[] split = ipPositioning.getRectangle().replaceAll("\\;", ",").split(",");
        System.out.println(Arrays.toString(split));
        String local = split[0] + "," + split[3];
        String weather = HttpUtil.get(String.format(APIConfig.qweatherURL_NOW, local));
        weather weather1 = JSON.parseObject(weather, weather.class);
        return weather1;
    }

    public static void main(String[] args) {
        new WeatherServiceImpl().getWeather(null);
    }
}
