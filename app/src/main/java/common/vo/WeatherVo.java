package common.vo;

import common.model.BaseResp;

/**
 * Created by Administrator on 2016/9/20.
 */

public class WeatherVo extends BaseResp {


    /**
     * msg : 返回正常
     * data : {"area":"贵阳","temperature":"18","weather_pic":"http://220.197.198.41:8080/dcbweather/resource/weatherpic/nav_weather_mostlycloudy.png"}
     * status : 200
     */

    private String msg;
    /**
     * area : 贵阳
     * temperature : 18
     * weather_pic : http://220.197.198.41:8080/dcbweather/resource/weatherpic/nav_weather_mostlycloudy.png
     */

    private DataBean data;
    private String status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        private String area;
        private String temperature;
        private String weather_pic;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWeather_pic() {
            return weather_pic;
        }

        public void setWeather_pic(String weather_pic) {
            this.weather_pic = weather_pic;
        }
    }
}
