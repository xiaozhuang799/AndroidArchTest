package com.act.androidarchtest;



import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.act.androidarchtest.base.BaseActivity;
import com.act.androidarchtest.mvc.MvcActivity;
import com.act.androidarchtest.mvp.MvpActivity;
import com.act.androidarchtest.mvvm.MvvmActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @BindView(R.id.bt_mvc)
    Button mBtMvc;
    @BindView(R.id.bt_mvp)
    Button mBtMvp;
    @BindView(R.id.bt_mvvm)
    Button mBtMvvm;



    @Override
    protected void initActivity() {

    }

    @OnClick({R.id.bt_mvc, R.id.bt_mvp, R.id.bt_mvvm})
    public void onButtonClicked(View view) {
        String text;
        switch (view.getId()) {
            case R.id.bt_mvc:
                Intent intent = new Intent(this, MvcActivity.class);
                startActivity(intent);
                text = mBtMvc.getText().toString();
                break;
            case R.id.bt_mvp:
                intent = new Intent(this, MvpActivity.class);
                startActivity(intent);
                text = mBtMvp.getText().toString();
                break;
            case R.id.bt_mvvm:
                intent = new Intent(this, MvvmActivity.class);
                startActivity(intent);
                text = mBtMvvm.getText().toString();
                break;
            default:
                text = "not found";
                break;
        }
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int bindActivityLayout() {
        return R.layout.activity_main;
    }
}
