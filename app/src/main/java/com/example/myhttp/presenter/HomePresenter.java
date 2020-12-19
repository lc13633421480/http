package com.example.myhttp.presenter;

import android.view.View;

import com.example.myhttp.bean.CityData;
import com.example.myhttp.bean.WeatherData;
import com.example.myhttp.model.HomeModel;
import com.example.myhttp.ui.HomeActivity;
import com.example.myhttp.view.CallBack;
import com.example.myhttp.view.HomeView;

import java.util.Map;

public class HomePresenter implements HomeView.Presenter{
    HomeView.View homeView;
    private final HomeModel homeModel;

    public HomePresenter(HomeView.View homeView) {
        this.homeView = homeView;
        homeModel = new HomeModel();
    }

    @Override
    public void getCity() {
        homeModel.getCity(new CallBack() {
            @Override
            public void Sucess(Object object) {
                if(homeView != null){
                    homeView.loading(View.GONE);
                    homeView.getCityReturn((CityData) object);
                }
            }

            @Override
            public void Faile(String msg) {
                if(homeView != null){
                    homeView.loading(View.GONE);
                    homeView.tips(msg);
                }
            }
        });
    }

    //获取天气，给V层
    @Override
    public void getWeather(Map<String, String> map) {
        homeModel.getWeather(map, new CallBack() {
            @Override
            public void Sucess(Object object) {
                if(homeView != null){
                    homeView.loading(View.GONE);
                    homeView.getWeatherReturn((WeatherData) object);
                }
            }

            @Override
            public void Faile(String msg) {
                if(homeView != null){
                    homeView.loading(View.GONE);
                    homeView.tips(msg);
                }
            }
        });
    }
}
