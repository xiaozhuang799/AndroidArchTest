package com.act.androidarchtest.mvp.model;


import com.act.androidarchtest.bean.WeatherBean;


public interface OnWeatherListener
{
    void onSuccess(WeatherBean weather);
    void onError();
}
