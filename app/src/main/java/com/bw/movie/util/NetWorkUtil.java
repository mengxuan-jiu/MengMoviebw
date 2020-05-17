package com.bw.movie.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author mengxuan
 * @包名 com.bw.movie.util
 * @MengXuanmengxuan
 * @日期2020/5/17
 * @项目名MengMoviebw
 * @类名NetWorkUtil
 **/
public class NetWorkUtil {
    private static final class PrivateNetUtil {
        private static final NetWorkUtil NET_UTIL = new NetWorkUtil();
    }

    public static NetWorkUtil getInstance() {
        return PrivateNetUtil.NET_UTIL;
    }

    public NetWorkUtil() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(8, TimeUnit.SECONDS)
                .callTimeout(8, TimeUnit.SECONDS)
                .readTimeout(8, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
