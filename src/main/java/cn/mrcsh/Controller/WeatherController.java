package cn.mrcsh.Controller;

import cn.mrcsh.Entity.Api.qweather.weather;
import cn.mrcsh.Entity.Result;
import cn.mrcsh.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 天气API
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class WeatherController {

    @Autowired
    private WeatherService service;

    /**
     * 天气接口
     *
     * @return <T>
     */
    @GetMapping("/weather")
    public Object getWeather(HttpServletRequest request) {
        weather weather = service.getWeather(request);
        return Result.success(weather.getNow());
    }
}
