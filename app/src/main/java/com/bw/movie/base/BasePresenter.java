package com.bw.movie.base;

/**
 * @author mengxuan
 * @包名 com.bw.movie.base
 * @MengXuanmengxuan
 * @日期2020/5/17
 * @项目名MengMoviebw
 * @类名BasePresenter
 **/
public abstract class BasePresenter<V> {
    public V view;

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void setView(V view) {
        this.view = view;
    }

    public void setView() {
        view = null;
    }
}
