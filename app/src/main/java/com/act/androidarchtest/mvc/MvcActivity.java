package com.act.androidarchtest.mvc;


import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.act.androidarchtest.R;
import com.act.androidarchtest.base.BaseActivity;
import com.act.androidarchtest.bean.WeatherBean;
import com.act.androidarchtest.mvc.model.IWeatherModel;
import com.act.androidarchtest.mvc.model.OnWeatherListener;
import com.act.androidarchtest.mvc.model.WeatherModelImpl;

import butterknife.BindView;
import butterknife.OnClick;

public class MvcActivity extends BaseActivity implements OnWeatherListener {

    @BindView(R.id.tv_weather_info)
    TextView mTvWeatherInfo;

    private static final int QUERY_WEATHER_SUCCESS = 1;
    private static final int QUERY_WEATHER_FAIL = 0;

    private IWeatherModel weatherModel;
    private WeatherBean weather;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case QUERY_WEATHER_FAIL:
                    Toast.makeText(getApplicationContext(), "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    break;
                case QUERY_WEATHER_SUCCESS:
                    mTvWeatherInfo.setText(weather.toString());
                    break;
            }
            return false;
        }
    });


    @Override
    protected void initActivity() {
        weatherModel = new WeatherModelImpl();
    }


    @OnClick(R.id.bt_query)
    public void onQueryClick() {
        weatherModel.getWeather(this);
    }


    @Override
    public void onSuccess(WeatherBean weather) {
        this.weather = weather;
        mHandler.sendEmptyMessage(QUERY_WEATHER_SUCCESS);
    }

    @Override
    public void onError() {
        mHandler.sendEmptyMessage(QUERY_WEATHER_FAIL);
    }

    @Override
    protected int bindActivityLayout() {
        return R.layout.activity_mvc;
    }

}
