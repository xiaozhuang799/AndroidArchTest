package com.act.androidarchtest.mvp.view;


import com.act.androidarchtest.bean.WeatherBean;


public interface IWeatherView {
    void updateWeather(WeatherBean weatherBean);
    void updateError();
}
