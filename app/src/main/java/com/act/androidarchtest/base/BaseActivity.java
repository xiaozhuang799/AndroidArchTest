package com.act.androidarchtest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity
{
    protected abstract int bindActivityLayout();
    protected abstract void initActivity();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindActivityLayout());
        ButterKnife.bind(this);

        initActivity();
    }
}
