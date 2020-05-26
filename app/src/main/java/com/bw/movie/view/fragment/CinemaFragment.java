package com.bw.movie.view.fragment;




import androidx.fragment.app.Fragment;

import android.view.View;


import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.presenter.PresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class CinemaFragment extends BaseFragment {

    @Override
    protected BasePresenter initPresenter() {
        return new PresenterImpl();
    }

    @Override
    protected void setListener() {

    }

    @Override
    public int getFragLayoutID() {
        return R.layout.fragment_cinema;
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void startCoding() {

    }

    @Override
    public void lazyLoad() {

    }


    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onError(String error) {

    }
}
