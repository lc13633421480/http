package com.example.myhttp.view;

import com.example.myhttp.bean.CityData;
import com.example.myhttp.bean.WeatherData;

import java.util.Map;

public interface HomeView{
    //V层接口
    interface View extends BaseView{
        //获取数据返回

        void getCityReturn(CityData result);

        void getWeatherReturn(WeatherData result);
    }

    //P层接口
    interface Presenter{
        void getCity();

        void getWeather(Map<String,String> map);
    }

    //M层接口
    interface Model{
        void getCity(CallBack callBack);
        //P层调用M层的接口
        void getWeather(Map<String,String> map,CallBack callBack);
    }
}
