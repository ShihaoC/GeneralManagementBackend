package cn.mrcsh.Controller;

import cn.mrcsh.Code.ErrorCode;
import cn.mrcsh.Entity.Api.qweather.weather;
import cn.mrcsh.Entity.Factory.ResponseFactory;
import cn.mrcsh.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class WeatherController {

    @Autowired
    private WeatherService service;

    @GetMapping("/weather")
    public Object getWeather(HttpServletRequest request){
        weather weather = service.getWeather(request);
        return new ResponseFactory<cn.mrcsh.Entity.Api.qweather.weather.now>().getInstance(weather.getNow(),"成功", ErrorCode.SUCCESS);
    }
}
