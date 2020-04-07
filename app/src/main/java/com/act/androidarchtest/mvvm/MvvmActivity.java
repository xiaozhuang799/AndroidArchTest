package com.act.androidarchtest.mvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.act.androidarchtest.R;
import com.act.androidarchtest.databinding.ActivityMvvmBinding;
import com.act.androidarchtest.mvvm.model.WeatherModelImpl;

public class MvvmActivity extends AppCompatActivity {

    private WeatherModelImpl weatherModel;

    private ActivityMvvmBinding mMvvmBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMvvmBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        weatherModel = new WeatherModelImpl();
        mMvvmBinding.setWeatherModel(weatherModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        weatherModel.onCancel();
    }
}
