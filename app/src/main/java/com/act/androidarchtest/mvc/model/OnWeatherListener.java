package com.act.androidarchtest.mvc.model;


import com.act.androidarchtest.bean.WeatherBean;


public interface OnWeatherListener
{
    void onSuccess(WeatherBean weather);
    void onError();
}
