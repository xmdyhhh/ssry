package com.ruoyi.studentSys.domain;

public class WeatherForecast implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private String date;
    private String ymd;
    private String week;
    private String sunrise;
    private String high;
    private String low;

    private Float highFloat;
    private Float lowFloat;

    private String sunset;
    private Float aqi;
    private String fx;
    private String fl;
    private String type;
    private String notice;

    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYmd() {
        return ymd;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }


    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public Float getAqi() {
        return aqi;
    }

    public void setAqi(Float aqi) {
        this.aqi = aqi;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        String temp = high.replace("高温","").replace("℃","");
        this.highFloat = Float.parseFloat(temp);
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        String temp = low.replace("低温","").replace("℃","");
        this.lowFloat = Float.parseFloat(temp);
        this.low = low;
    }

    public Float getHighFloat() {
        return highFloat;
    }

    public void setHighFloat(Float highFloat) {
        this.highFloat = highFloat;
    }

    public Float getLowFloat() {
        return lowFloat;
    }

    public void setLowFloat(Float lowFloat) {
        this.lowFloat = lowFloat;
    }


    //       "date": "22",
//               "ymd": "2018-09-22",   //年月日  （新增）
//               "week": "星期六",       //星期 （新增）
//               "sunrise": "05:57",
//               "high": "高温 26.0℃",
//               "low": "低温 15.0℃",
//               "sunset": "18:10",
//               "aqi": 55.0,
//               "fx": "西北风",
//               "fl": "4-5级",
//               "type": "晴",
//               "notice": "愿你拥有比阳光明媚的心情"
    
}