package com.act.androidarchtest.mvp.presenter;

import com.act.androidarchtest.bean.WeatherBean;
import com.act.androidarchtest.mvp.model.IWeatherModel;
import com.act.androidarchtest.mvp.model.OnWeatherListener;
import com.act.androidarchtest.mvp.model.WeatherModelImpl;
import com.act.androidarchtest.mvp.view.IWeatherView;


public class WeatherPresenter {

    private IWeatherModel weatherModel;
    private IWeatherView weatherView;

    public WeatherPresenter(IWeatherView weatherView) {
        this.weatherView = weatherView;
        weatherModel = new WeatherModelImpl(new OnWeatherListener() {
            @Override
            public void onSuccess(WeatherBean weather) {
                WeatherPresenter.this.weatherView.updateWeather(weather);
            }

            @Override
            public void onError() {
                WeatherPresenter.this.weatherView.updateError();
            }
        });
    }

    public void getWeather() {
        weatherModel.getWeather();
    }

    public void detachedView() {
        weatherView = null;
        weatherModel.onCancel();
    }

}
