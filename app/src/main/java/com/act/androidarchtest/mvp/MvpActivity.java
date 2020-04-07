package com.act.androidarchtest.mvp;



import android.widget.TextView;
import android.widget.Toast;

import com.act.androidarchtest.R;
import com.act.androidarchtest.base.BaseActivity;
import com.act.androidarchtest.bean.WeatherBean;
import com.act.androidarchtest.mvp.presenter.WeatherPresenter;
import com.act.androidarchtest.mvp.view.IWeatherView;

import butterknife.BindView;
import butterknife.OnClick;


public class MvpActivity extends BaseActivity implements IWeatherView {


    @BindView(R.id.tv_weather_info)
    TextView mTvWeatherInfo;

    private WeatherPresenter weatherPresenter;

    @Override
    protected void initActivity() {
        weatherPresenter = new WeatherPresenter(this);
    }

    @OnClick(R.id.bt_query)
    public void onQuery() {
        weatherPresenter.getWeather();
    }

    @Override
    public void updateWeather(WeatherBean weatherBean) {
        mTvWeatherInfo.setText(weatherBean.toString());
    }

    @Override
    public void updateError() {
        Toast.makeText(this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int bindActivityLayout() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void onDestroy() {
        weatherPresenter.detachedView();
        super.onDestroy();
    }
}
