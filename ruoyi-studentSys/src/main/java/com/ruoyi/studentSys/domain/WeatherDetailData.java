package com.ruoyi.studentSys.domain;

import java.util.List;

public class WeatherDetailData implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private String shidu;
    private Float shiduFloat;


    private Float pm25;
    private Float pm10;
    private String quality;
    private Float wendu;
    private String ganmao;//感冒提醒（指数）

    List<WeatherForecast> forecast;


    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
        String numberStr = shidu.replace("%", "");
        this.shiduFloat  = Float.parseFloat(numberStr) ;

    }

    public Float getShiduFloat() {
        return shiduFloat;
    }

    public void setShiduFloat(Float shiduFloat) {
        this.shiduFloat = shiduFloat;
    }




    public Float getPm25() {
        return pm25;
    }

    public void setPm25(Float pm25) {
        this.pm25 = pm25;
    }

    public Float getPm10() {
        return pm10;
    }

    public void setPm10(Float pm10) {
        this.pm10 = pm10;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Float getWendu() {
        return wendu;
    }

    public void setWendu(Float wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public List<WeatherForecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<WeatherForecast> forecast) {
        this.forecast = forecast;
    }
}



