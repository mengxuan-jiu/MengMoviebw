package com.bw.movie.contract;

import java.util.Map;

/**
 * @author mengxuan
 * @包名 com.bw.movie.contract
 * @MengXuanmengxuan
 * @日期2020/5/20
 * @项目名MengMoviebw
 * @类名IContart
 **/
public interface IContart {
    //用来获取数据
    interface IModel{
        void getInfoNoParams(String url, Class cls, ModelCallback modelCallback);
       void getInfoHaveParams(String url, Class cls, Map<String, Object> map, ModelCallback modelCallback);
       void getInfoHaveParams(String url, Class cls, Map<String, Object> h_map, Map<String, Object> map, ModelCallback modelCallback);
        //post
        void postInfoHaveParams(String url, Class cls, Map<String, Object> map, ModelCallback modelCallback);
        void postInfoHaveParams(String url, Class cls, Map<String, Object> h_map, Map<String, Object> map, ModelCallback modelCallback);
    }

    interface IView<T>{
        void onSuccess(T t);
        void onError(String error);
    }

    interface IPresenter{
        void startRequest(String url, Class cls, Map<String, Object> map);
        void startRequest(String url, Class cls, Map<String, Object> h_map, Map<String, Object> map);
        void startRequest(String url, Class cls);
        void postRequest(String url, Class cls, Map<String, Object> map);
        void postRequest(String url, Class cls, Map<String, Object> h_map, Map<String, Object> map);
    }

    interface ModelCallback<T>{
        void onSuccess(T t);

        void onError(String error);
    }
}
