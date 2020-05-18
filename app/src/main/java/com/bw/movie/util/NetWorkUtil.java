package com.bw.movie.util;

import com.bw.movie.url.Api;
import com.bw.movie.apiservice.ApiService;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
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
 * @类名网络工具
 **/
public class NetWorkUtil {

    private final ApiService mApiService;

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
                .writeTimeout(6, TimeUnit.SECONDS)
                .callTimeout(6, TimeUnit.SECONDS)
                .readTimeout(6, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.URL_OUTER)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    public ApiService getmApiService() {
        return mApiService;
    }

    public  void  getNoParameters(String url){
        mApiService.getNoParameters(url)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe();

    }
    public  void  getHaveParameters(Class myclass,String url,Map<String , Object> map){
        mApiService.getHaveParameters(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
