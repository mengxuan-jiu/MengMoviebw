package com.bw.movie.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bw.movie.contract.IContart;

/**
 * @author mengxuan
 * @包名 com.bw.movie.base
 * @MengXuanmengxuan
 * @日期2020/5/22
 * @项目名MengMoviebw
 * @类名BaseFragment
 **/
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IContart.IView    {
    public P mPresenter;
    private final String TAG = "BaseFragment";
    //判断当前Fragment 是否可见
    private boolean isUserHint;
    //判断当前fragment里面初始化任务是否执行过
    private boolean isViewLoad;
    //加载的大的布局抽成成员变量
    private View mContentView;
    //用来优化网络操作 执行过的耗时操作再次进来不用重新请求了
    private boolean isDataLoad;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        //onCreateView会执行多次 对象创建的时候只有不为空的创建
        if (mContentView == null) {
            mContentView = createContentView();
        }
        return mContentView;
    }

    //创建加载试图  共性的代码抽取
    private View createContentView() {
        View view = null;
        if (getFragLayoutID() != 0) {
            view = getLayoutInflater().inflate(getFragLayoutID(), null);
            initViews(view);

            setListener();
            if (mPresenter == null) {
                mPresenter = initPresenter();
                mPresenter.onAttch(this);
            }
            startCoding();
        } else {
            new IllegalArgumentException("getContentLayout must View or LayoutId");
        }
        return view;
    }

    protected abstract P initPresenter();

    protected abstract void setListener();




    private void loadData() {
        //懒加载触发的条件就是
        /**
         * 1：保证用户可见
         * 2：保证View已经初始化完成
         * 3：要保证加仔完成过后就不能再次加载
         */
        if (isUserHint && isViewLoad && (!isDataLoad)) {
            lazyLoad();
            isDataLoad = true;
        }
    }
    public abstract int getFragLayoutID();

    public abstract void initViews(View view);

    public abstract void startCoding();

    //用来做耗时操作的方法
    public abstract void lazyLoad();


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated");
        if (!isViewLoad) {//一次默认是false
            initViews(mContentView);
             startCoding();
        }
        isViewLoad = true;
        loadData();
    }


    /**
     * //只有在Fragment和Viewpager交互的时候才会执行
     * //一进来会执行两次
     * 1：Fragment被加载执行的
     * 2：当前需要展示的Fragment
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG, "setUserVisibleHint");
        this.isUserHint = isVisibleToUser;//只有可见的为true
        loadData();
    }

    // context this, cs跳转对象 bundle 传递参数
    public void Intent(Context context, Class<?> cs, Bundle bundle) {
        Intent i = new Intent(context, cs);
        if (bundle != null)
            i.putExtras(bundle);
        context.startActivity(i);
    }
}