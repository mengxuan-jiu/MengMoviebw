package com.bw.movie.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bw.movie.R;

/**
 * @author mengxuan
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
    }

    protected abstract int layoutId();
}
