package com.bw.movie.view;



import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.presenter.PresenterImpl;

/**
 * @author mengxuan
 * MVP
 * ok+rx
 * Fresco
 * sp
 *登录
 */
public class MainActivity extends BaseActivity {
    private SharedPreferences.Editor edit;
    private SharedPreferences sp;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
            edit = sp.edit();
            edit.putBoolean("isorno", true);
            edit.apply();
        }
    };
    @Override
    public BasePresenter initPresenter() {
        return new PresenterImpl();
    }

    @Override
    public int getLayoutId() {
          return R.layout.activity_main;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void startCoding() {
        sp = getPreferences(MODE_PRIVATE);
        boolean isorno = sp.getBoolean("isorno", false);
        if (isorno) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            handler.sendEmptyMessageDelayed(0, 3000);

        }
    }

    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onError(String error) {

    }


}
