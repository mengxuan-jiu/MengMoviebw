package com.bw.movie.view;


import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.view.fragment.CinemaFragment;
import com.bw.movie.view.fragment.FilmFragment;
import com.bw.movie.view.fragment.MineFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {


    private ViewPager vp;
    private TabLayout tag;

    @Override
    public BasePresenter initPresenter() {
        return new PresenterImpl();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initViews() {
        vp=findViewById(R.id.vp);
        tag=findViewById(R.id.tag);
    }

    @Override
    public void setListener() {



    }

    @Override
    public void startCoding() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new FilmFragment());
        list.add(new CinemaFragment());
        list.add(new MineFragment());
        vp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
tag.setupWithViewPager(vp);
    }



    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onError(String error) {

    }

}
