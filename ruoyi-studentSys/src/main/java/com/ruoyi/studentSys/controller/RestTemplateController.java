package com.ruoyi.studentSys.controller;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.studentSys.domain.WeatherForecast;
import com.ruoyi.studentSys.domain.WeatherReturnData;
import com.ruoyi.studentSys.service.IDCardService;
import com.ruoyi.studentSys.util.WeatherIconUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class RestTemplateController  extends BaseController {

    @GetMapping("/resttemplate/tianqi")
    public String tianqi() {
        return "studentSys/restTemplate/tianqi";
    }


    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/resttemplate/getTianQi1")
    @ResponseBody
    public AjaxResult getTianQi1(String cityId) {
        cityId = "101030100";
        AjaxResult success = AjaxResult.success();
        String url = "http://t.weather.sojson.com/api/weather/city/" + cityId;

        String result = restTemplate.getForObject(url, String.class);
        success.put("data", result);
        return success;

    }

    @RequestMapping("/resttemplate/getTianQi2")
    @ResponseBody
    public AjaxResult getTianQi2(String cityCode) {

        AjaxResult success = AjaxResult.success();
        String url = "http://t.weather.sojson.com/api/weather/city/" + cityCode;

        WeatherReturnData returnData = restTemplate.getForObject(url, WeatherReturnData.class);
        success.put("weather", returnData);

        HashMap<String, Object> forecastData = new HashMap<>();
        success.put("forecastData", forecastData);

        List<String> days =new ArrayList<>();
        List<String> dates =new ArrayList<>();
        List<Float> highTemp =new ArrayList<>();
        List<Float> lowTemp =new ArrayList<>();
        List<String> weather =new ArrayList<>();
        List<String> icons =new ArrayList<>();
        List<Float> humidity =new ArrayList<>();
        List<String> wind =new ArrayList<>();
        forecastData.put("days", days);
        forecastData.put("dates", dates);
        forecastData.put("highTemp", highTemp);
        forecastData.put("lowTemp", lowTemp);
        forecastData.put("weather", weather);
        forecastData.put("icons", icons);
        forecastData.put("humidity", humidity);
        forecastData.put("wind", wind);

        for (int i=1;i<8;i++)
        {
            WeatherForecast forecast = returnData.getData().getForecast().get(i);
            days.add(forecast.getWeek());
            dates.add(forecast.getYmd());
            highTemp.add(forecast.getHighFloat());
            lowTemp.add(forecast.getLowFloat());
            weather.add(forecast.getType());
            icons.add(WeatherIconUtil.getIconName(forecast.getType()));
            humidity.add(forecast.getAqi());
            wind.add(forecast.getFx()  +forecast.getFl());
        }

        return success;
    }

    @GetMapping("/resttemplate/sfz")
    public String sfz() {
        return "studentSys/restTemplate/sfz";
    }


    @Autowired
    IDCardService idCardService;


    @PostMapping("/resttemplate/sfzfront")
    @ResponseBody
    public AjaxResult sfzfront(MultipartFile file) throws Exception
    {
        try {
            AjaxResult result = idCardService.getIDCardOCR(file,"front");
            return result;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }

    }

    @PostMapping("/resttemplate/sfzback")
    @ResponseBody
    public AjaxResult sfzback(MultipartFile file) throws Exception
    {
        try {
            AjaxResult result = idCardService.getIDCardOCR(file,"back");
            return result;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }

    }
}