package com.example.myhttp.net;

import android.text.format.Time;

import com.example.myhttp.api.ApiService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO   网络请求类
public class HttpManger {
    private static HttpManger instance;

    public static HttpManger getInstance(){
        if(instance == null){
            synchronized (HttpManger.class){
                if(instance == null){
                    instance = new HttpManger();
                }
            }
        }
        return instance;
    }

    //封装的Retrofit
    private Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.BASE_URL).client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    //得到拦截器
    private OkHttpClient getOkHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60,TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .build();
        return okHttpClient;
    }

    //日志拦截器
    static class LoggingInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response proceed = chain.proceed(request);
            return proceed;
        }
    }

    //头部拦截器
    static class HeaderInterceptor implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("Authorization","APPCODE 964e16aa1ae944e9828e87b8b9fbd30a")
                    .build();
            return chain.proceed(request);
        }
    }

    private ApiService apiService;

    public ApiService getApiService(){
        if(apiService == null){
            apiService = getRetrofit().create(ApiService.class);
        }
        return apiService;
    }
}
