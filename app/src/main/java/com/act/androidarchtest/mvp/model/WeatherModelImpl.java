package com.act.androidarchtest.mvp.model;


import android.os.Handler;
import android.os.Message;

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
    private static final int QUERY_WEATHER_SUCCESS = 1;
    private static final int QUERY_WEATHER_FAIL = 0;

    private OnWeatherListener onWeatherListener;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (null == onWeatherListener) return false;
            switch (msg.what) {

                case QUERY_WEATHER_SUCCESS:
                    WeatherBean weather = (WeatherBean) msg.obj;
                    onWeatherListener.onSuccess(weather);
                    break;

                case QUERY_WEATHER_FAIL:
                    onWeatherListener.onError();
                    break;
            }
            return false;
        }
    });

    public WeatherModelImpl(OnWeatherListener onWeatherListener) {
        this.onWeatherListener = onWeatherListener;
    }

    @Override
    public void getWeather() {
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
                Message message = new Message();
                message.what = QUERY_WEATHER_FAIL;
                mHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Gson gson = new Gson();
                WeatherBean weather = gson.fromJson(body, WeatherBean.class);
                Message message = new Message();
                message.what = QUERY_WEATHER_SUCCESS;
                message.obj = weather;
                mHandler.sendMessage(message);
            }
        });

    }

    @Override
    public void onCancel() {
        mHandler.removeMessages(mHandler.obtainMessage().what);
    }
}
