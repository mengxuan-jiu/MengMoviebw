package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.contract.IContart;
import com.bw.movie.model.ModeImpl;

import java.util.Map;

/**
 * @author mengxuan
 * @包名 com.bw.movie.presenter
 * @MengXuanmengxuan
 * @日期2020/5/20
 * @项目名MengMoviebw
 * @类名PresenterImpl
 **/
public class PresenterImpl extends BasePresenter {
    private ModeImpl mModel;
    @Override
    protected void initModel() {
        mModel = new ModeImpl();
    }

    @Override
    public void startRequest(String url, Class cls, Map<String, Object> map) {

        mModel.getInfoHaveParams(url, cls, map, new IContart.ModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onError(String error) {
                getView().onError(error);
            }
        });

    }

    @Override
    public void startRequest(String url, Class cls, Map<String, Object> h_map, Map<String, Object> map) {
        mModel.getInfoHaveParams(url, cls, h_map, map, new IContart.ModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onError(String error) {
                getView().onError(error);
            }
        });
    }

    @Override
    public void startRequest(String url, Class cls) {
        mModel.getInfoNoParams(url, cls, new IContart.ModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onError(String error) {
                getView().onError(error);
            }
        });
    }

    @Override
    public void postRequest(String url, Class cls, Map<String, Object> map) {
        mModel.postInfoHaveParams(url, cls, map, new IContart.ModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onError(String error) {
                getView().onError(error);
            }
        });
    }

    @Override
    public void postRequest(String url, Class cls, Map<String, Object> h_map, Map<String, Object> map) {
        mModel.postInfoHaveParams(url, cls, h_map, map, new IContart.ModelCallback() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onError(String error) {
                getView().onError(error);
            }
        });
    }
}

