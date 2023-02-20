package cn.mrcsh.Service;

import cn.mrcsh.Entity.Api.qweather.weather;

import javax.servlet.http.HttpServletRequest;

public interface WeatherService {


    weather getWeather(HttpServletRequest request);
}
