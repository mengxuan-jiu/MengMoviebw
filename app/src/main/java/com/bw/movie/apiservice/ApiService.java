package com.bw.movie.apiservice;

import android.renderscript.Allocation;

import com.bw.movie.App;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @author
 * @包名 com.bw.movie
 * @MengXuanmengxuan
 * @日期2020/4/17
 * @项目名Movie
 * @类名ApiS 服务
 **/
public interface ApiService {
    @GET
    Observable<ResponseBody> getNoParameters(@Url String url);

    @GET
    Observable<ResponseBody> getHaveParameters(@Url String url, @QueryMap Map<String,Object> map);

  
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postHaveParameters(@Url String url, @FieldMap Map< String,Object> map);
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postParameters(@Url String url, @HeaderMap Map< String,Object> h_map, @FieldMap Map< String,Object> map);
}
