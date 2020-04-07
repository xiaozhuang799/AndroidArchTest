package com.act.androidarchtest.mvvm.model;


import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;


import com.act.androidarchtest.bean.WeatherBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WeatherModelImpl implements IWeatherModel
{

    public final ObservableBoolean querySuccess = new ObservableBoolean(false);

    public final ObservableField<String> weatherInfo = new ObservableField<>();

    private Call mCall;

    public WeatherModelImpl() { }

    @Override
    public void getWeather() {
        String url = "http://www.weather.com.cn/data/sk/101280101.html";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        mCall = okHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                querySuccess.set(false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Gson gson = new Gson();
                WeatherBean weather = gson.fromJson(body, WeatherBean.class);
                querySuccess.set(true);
                weatherInfo.set(weather.toString());
            }
        });

    }

    @Override
    public void onCancel() {
        if (null != mCall) {
            mCall.cancel();
        }
    }
}
