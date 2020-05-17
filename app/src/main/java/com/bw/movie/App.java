package com.bw.movie;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author mengxuan
 * @包名 com.bw.movie
 * @MengXuanmengxuan
 * @日期2020/5/17
 * @项目名MengMoviebw
 * @类名App
 **/
public class App extends Application {
    public static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);


    }
}
