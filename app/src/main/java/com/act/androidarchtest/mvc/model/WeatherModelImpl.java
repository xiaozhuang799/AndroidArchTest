package com.act.androidarchtest.mvc.model;


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
    @Override
    public void getWeather(final OnWeatherListener onWeatherListener) {
        String url = "http://www.weather.com.cn/data/sk/101280101.html";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onWeatherListener.onError();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Gson gson = new Gson();
                WeatherBean weather = gson.fromJson(body, WeatherBean.class);
                onWeatherListener.onSuccess(weather);
            }
        });

    }
}
