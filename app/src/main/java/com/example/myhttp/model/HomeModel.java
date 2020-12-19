package com.example.myhttp.model;

import com.example.myhttp.api.ApiService;
import com.example.myhttp.bean.CityData;
import com.example.myhttp.bean.WeatherData;
import com.example.myhttp.net.HttpManger;
import com.example.myhttp.view.CallBack;
import com.example.myhttp.view.HomeView;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeModel implements HomeView.Model {

    @Override
    public void getCity(final CallBack callBack) {
        ApiService api = HttpManger.getInstance().getApiService();
        api.getCity().enqueue(new retrofit2.Callback<CityData>() {
            @Override
            public void onResponse(Call<CityData> call, Response<CityData> response) {
                callBack.Sucess(response.body());
            }

            @Override
            public void onFailure(Call<CityData> call, Throwable t) {
                callBack.Faile(t.toString());
            }
        });
    }

    @Override
    public void getWeather(Map<String, String> map, final CallBack callBack) {
        ApiService api = HttpManger.getInstance().getApiService();
        api.queryWeather(map).enqueue(new retrofit2.Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if(response.code() == 200){
                    callBack.Sucess(response.body());
                }else{
                    try {
                        String str = response.errorBody().string().toString();
                        callBack.Faile(str);
                    } catch (IOException e) {
                        callBack.Faile(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                callBack.Faile(t.getMessage());
            }
        });
    }
}
