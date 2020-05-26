package com.bw.movie.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.bw.movie.App;
import com.bw.movie.url.Api;
import com.bw.movie.apiservice.ApiService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
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
//                .addInterceptor(new Interceptor() {
//            //我们自定义一个拦截器 优化请求头的
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                //登陆的时候就可以把userId和sessionId保存在本地
//                SharedPreferences lvxx = App.sContext.getSharedPreferences("lvxx", Context.MODE_PRIVATE);
//                String userId = lvxx.getString("userId", "");
//                String sessionId = lvxx.getString("sessionId", "");
//                if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(sessionId)) {
//                    return chain.proceed(chain.request());//之前的那个request你正常使用 我这么对你没有用
//                } else {
//                    Request request = chain.request().newBuilder()
//                            .addHeader("userId", userId)
//                            .addHeader("sessionId", sessionId)
//                            .build();
//                    Log.e("userid", userId);
//                    Log.e("sessionId", sessionId);
//                    return chain.proceed(request);
//                }
//            }
//        })
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
    //这个方法是通过model来调用的
    public void postInfo(String url, final Class cls,Map<String, Object> h_map, Map<String, Object> map, final NetCallback netCallback) {

        mApiService.postHaveParameters(url, map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Gson gson = new Gson();
                            //参数1：json串 参数2：MyData.class
                            Object object = gson.fromJson(string, cls);
                            if (netCallback != null) {
                                netCallback.onSuccess(object);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (netCallback != null) {
                            netCallback.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //这个方法是通过model来调用的
    public void postInfo(String url, final Class cls, Map<String, Object> map, final NetCallback netCallback) {

        mApiService.postHaveParameters(url, map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Gson gson = new Gson();
                            //参数1：json串 参数2：MyData.class
                            Object object = gson.fromJson(string, cls);
                            if (netCallback != null) {
                                netCallback.onSuccess(object);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (netCallback != null) {
                            netCallback.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //这个方法是通过model来调用的
    public void getInfo(String url, final Class cls, Map<String, Object> map, final NetCallback netCallback) {
        mApiService.getHaveParameters(url, map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Gson gson = new Gson();
                            //参数1：json串 参数2：MyData.class
                            Object object = gson.fromJson(string, cls);
                            if (netCallback != null) {
                                netCallback.onSuccess(object);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (netCallback != null) {
                            netCallback.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getInfo(String url, final Class cls, Map<String, Object> map_h,Map<String, Object> map, final NetCallback netCallback) {
        mApiService.getHaveParameters(url, map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Gson gson = new Gson();
                            //参数1：json串 参数2：MyData.class
                            Object object = gson.fromJson(string, cls);
                            if (netCallback != null) {
                                netCallback.onSuccess(object);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (netCallback != null) {
                            netCallback.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getInfo(String url, final Class cls, final NetCallback netCallback) {
        mApiService.getNoParameters(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Gson gson = new Gson();
                            //参数1：json串 参数2：MyData.class
                            Object object = gson.fromJson(string, cls);
                            if (netCallback != null) {
                                netCallback.onSuccess(object);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (netCallback != null) {
                            netCallback.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //是吧当前网络工具类的数据回传给Mode层
    public interface NetCallback<T> {
        void onSuccess(T t);

        void onError(String error);
    }
}
