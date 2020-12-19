package com.example.myhttp.api;

import com.example.myhttp.bean.CityData;
import com.example.myhttp.bean.WeatherData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiService {
      String BASE_URL = "https://jisutqybmf.market.alicloudapi.com/weather/";

      @GET("city")
      Call<CityData>getCity();

      @GET("query")
      Call<WeatherData>queryWeather(@QueryMap Map<String,String> map);

}
