package com.example.zweather.h;

/**
 * Created by zh231 on 2018/3/14 0014.
 */

public class Mode {

    private static String msg;//消息
    private static int status;//状态
    private static String city;//城市
    private static String cityid;//城市id
    private static String citycode;//城市代码
    private static String date;//今天日期
    private static String week;//星期几
    private static String weather;//天气
    private static String temp;//当前温度
    private static String temphigh;//最高温度
    private static String templow;//最低温度
    private static String img;//图片id
    private static String humidity;//湿度
    private static String pressure;//气压
    private static String windspeed;//风速
    private static String winddirect;//风向
    private static String windpower;//风等级
    private static String updatetime;//天气更新时间
    private static String[] iname = new String[7];//index数组标题
    private static String[] ivalue = new String[7];//index数组值
    private static String[] detail = new String[7];//index数组详情
    private static String aqi;//空气质量指数
    private static String quality;//空气质量
    private static String pm2_5;//pm2.5指数
    private static String pm10;//pm10指数
    private static String so2;//二氧化硫
    private static String[] daily_sunrise = new String[7];//日出
    private static String[] daily_sunset = new String[7];//日落
    private static String[] daily_temphight = new String[7];//每日最高
    private static String[] daily_templow = new String[7];//每日最低
    private static String[] daily_week = new String[7];//每日星期几
    private static String[] daily_day_weather=new String[7];//每日白天天气
    private static String[] daily_night_weather = new String[7];//每日夜晚天气
    private static String[] daily_night_img = new String[7];//每日晚上图片
    private static String[] daily_day_img = new String[7];//每日晚上图片


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemphigh() {
        return temphigh;
    }

    public void setTemphigh(String temphigh) {
        this.temphigh = temphigh;
    }

    public String getTemplow() {
        return templow;
    }

    public void setTemplow(String templow) {
        this.templow = templow;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getWinddirect() {
        return winddirect;
    }

    public void setWinddirect(String winddirect) {
        this.winddirect = winddirect;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIname(int i) {
        return iname[i];
    }

    public void setIname(String iname, int i) {
        this.iname[i] = iname;
    }

    public String getIvalue(int i) {
        return ivalue[i];
    }

    public void setIvalue(String ivalue, int i) {
        this.ivalue[i] = ivalue;
    }

    public String getDetail(int i) {
        return detail[i];
    }

    public void setDetail(String detail, int i) {
        this.detail[i] = detail;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getDaily_sunrise(int i) {
        return daily_sunrise[i];
    }

    public void setDaily_sunrise(String daily_sunrise, int i) {
        this.daily_sunrise[i] = daily_sunrise;
    }

    public String getDaily_sunset(int i) {
        return daily_sunset[i];
    }

    public void setDaily_sunset(String daily_sunset, int i) {
        this.daily_sunset[i] = daily_sunset;
    }

    public String getDaily_temphight(int i) {
        return daily_temphight[i];
    }

    public void setDaily_temphight(String daily_temphight, int i) {
        this.daily_temphight[i] = daily_temphight;
    }

    public String getDaily_templow(int i) {
        return daily_templow[i];
    }

    public void setDaily_templow(String daily_templow, int i) {
        this.daily_templow[i] = daily_templow;
    }

    public String getDaily_week(int i) {
        return daily_week[i];
    }

    public void setDaily_week(String daily_week, int i) {
        this.daily_week[i] = daily_week;
    }

    public String getDaily_night_weather(int i) {
        return daily_night_weather[i];
    }

    public void setDaily_night_weather(String daily_night_weather, int i) {
        this.daily_night_weather[i] = daily_night_weather;
    }

    public String getDaily_day_weather(int i) {
        return daily_day_weather[i];
    }

    public void setDaily_day_weather(String daily_day_weather, int i) {
        this.daily_day_weather[i] = daily_day_weather;
    }

    public String getDaily_night_img(int i) {
        return daily_night_img[i];
    }

    public void setDaily_night_img(String daily_night_img, int i) {
        this.daily_night_img[i] = daily_night_img;
    }

    public String getDaily_day_img(int i) {
        return daily_day_img[i];
    }

    public void setDaily_day_img(String daily_day_img, int i) {
        this.daily_day_img[i] = daily_day_img;
    }

    public String getPm2_5() {
        return pm2_5;
    }

    public void setPm2_5(String pm2_5) {
        this.pm2_5 = pm2_5;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }
}
