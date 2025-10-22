package com.ruoyi.studentSys.util;

public class WeatherIconUtil {

    public static String getIconName(String weather) {
        if(weather.contains("雨"))
            return "icon_yu";
        else if(weather.contains("雪"))
            return "icon_xue";
        else if(weather.contains("雷"))
            return "icon_lei";
        else if(weather.contains("雾"))
            return "icon_wu";
        else if(weather.contains("云"))
            return "icon_yun";
        else if(weather.contains("阴"))
            return "icon_yin";
        else if(weather.contains("沙"))
            return "icon_shachen";
        else if(weather.contains("雹"))
            return "icon_bingbao";
        else
            return "icon_qing";

    }



}
