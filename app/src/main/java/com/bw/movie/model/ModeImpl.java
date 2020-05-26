package com.bw.movie.model;

import com.bw.movie.contract.IContart;
import com.bw.movie.util.NetWorkUtil;

import java.util.Map;

/**
 * @author mengxuan
 * @包名 com.bw.movie.model
 * @MengXuanmengxuan
 * @日期2020/5/20
 * @项目名MengMoviebw
 * @类名ModeImpl
 **/
public class ModeImpl implements IContart.IModel {

    //这是get请求没有参数
    @Override
    public void getInfoNoParams(String url, Class cls, IContart.ModelCallback modelCallback) {
        NetWorkUtil.getInstance().getInfo(url, cls, new NetWorkUtil.NetCallback() {
            @Override
            public void onSuccess(Object o) {
                modelCallback.onSuccess(o);
            }

            @Override
            public void onError(String error) {
                modelCallback.onError(error);
            }
        });
    }

    //get请求有参数的
    @Override
    public void getInfoHaveParams(String url, Class cls, Map<String, Object> map, final IContart.ModelCallback modelCallback) {
        NetWorkUtil.getInstance().getInfo(url, cls, map, new NetWorkUtil.NetCallback() {
            @Override
            public void onSuccess(Object o) {
                modelCallback.onSuccess(o);
            }

            @Override
            public void onError(String error) {
                modelCallback.onError(error);
            }
        });
    }

    @Override
    public void getInfoHaveParams(String url, Class cls, Map<String, Object> h_map, Map<String, Object> map, IContart.ModelCallback modelCallback) {
        NetWorkUtil.getInstance().getInfo(url, cls, h_map, map, new NetWorkUtil.NetCallback() {
            @Override
            public void onSuccess(Object o) {
                modelCallback.onSuccess(o);
            }

            @Override
            public void onError(String error) {
                modelCallback.onError(error);
            }
        });
    }

    @Override
    public void postInfoHaveParams(String url, Class cls, Map<String, Object> map, IContart.ModelCallback modelCallback) {
        NetWorkUtil.getInstance().postInfo(url, cls, map, new NetWorkUtil.NetCallback() {
            @Override
            public void onSuccess(Object o) {
                modelCallback.onSuccess(o);
            }

            @Override
            public void onError(String error) {
                modelCallback.onError(error);

            }
        });
    }

    @Override
    public void postInfoHaveParams(String url, Class cls, Map<String, Object> h_map, Map<String, Object> map, IContart.ModelCallback modelCallback) {
        NetWorkUtil.getInstance().postInfo(url, cls, h_map, map, new NetWorkUtil.NetCallback() {
            @Override
            public void onSuccess(Object o) {
                modelCallback.onSuccess(o);
            }

            @Override
            public void onError(String error) {
                modelCallback.onError(error);

            }
        });
    }
}
