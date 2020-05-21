package com.bw.movie.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bw.movie.contract.IContart;

/**
 * @author mengxuan
 * @包名 com.bw.movie.base
 * @MengXuanmengxuan
 * @日期2020/5/20
 * @项目名MengMoviebw
 * @类名BaseActivity
 **/
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IContart.IView, View.OnClickListener {
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    //初始化P曾对象
    public abstract P initPresenter();

    //初始化加载布局ID
    public abstract int getLayoutId();

    //初始化控件
    public abstract void initViews();

    //可选
    public abstract void setListener();

    //接受上个页面传递到值 可选到
    public void initData() {
    }

    //可以写代码了
    public abstract void startCoding();

    void init() {
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            initViews();
            initData();
            setListener();
            if (mPresenter == null) {
                mPresenter = initPresenter();
                mPresenter.onAttch(this);
            }
            startCoding();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.onDeAttch();
            mPresenter = null;
        }
    }
}
