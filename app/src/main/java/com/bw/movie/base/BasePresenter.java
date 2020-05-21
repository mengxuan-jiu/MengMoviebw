package com.bw.movie.base;

import com.bw.movie.contract.IContart;

import java.lang.ref.WeakReference;

/**
 * @author mengxuan
 * @包名 com.bw.movie.base
 * @MengXuanmengxuan
 * @日期2020/5/20
 * @项目名MengMoviebw
 * @类名BasePresenter
 **/
public abstract class BasePresenter<V extends IContart.IView> implements IContart.IPresenter {
    private WeakReference<V> mWeak;

    //P绑定View
    public void onAttch(V v) {
        mWeak = new WeakReference<>(v);
    }

    //解绑
    public void onDeAttch() {
        if (mWeak != null && mWeak.get() != null) {
            mWeak.clear();
            mWeak = null;
        }
    }
    //提供一个方法让子类能获取到view层对象能操作
    public V getView() {
        return mWeak.get();
    }
    //写个能初始化model方法即可

    protected abstract void initModel();

    public BasePresenter() {
        initModel();
    }
}