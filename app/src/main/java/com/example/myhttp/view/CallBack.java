package com.example.myhttp.view;

public interface CallBack<T> {
    void Sucess(T t);
    void Faile(String msg);
}
