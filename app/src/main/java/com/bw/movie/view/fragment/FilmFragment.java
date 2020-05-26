package com.bw.movie.view.fragment;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.band.Banner;
import com.bw.movie.band.FindComingSoonMovieList;
import com.bw.movie.band.FindHotMovieList;
import com.bw.movie.band.FindReleaseMovieList;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.url.Api;

import com.bw.movie.view.andapter.AdapterFindHotMovie;
import com.bw.movie.view.andapter.AdapterFindReleaseMovie;
import com.bw.movie.view.andapter.AdapterfindComingSoonMovie;
import com.stx.xhb.xbanner.XBanner;


import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFragment extends BaseFragment {
    private LinearLayout coming;
    private LinearLayout popular;
    private LinearLayout hot;

    private RecyclerView hot_showing;
    private RecyclerView coming_soon;
    private RecyclerView h_find_hot_movie;
    private XBanner mXBanner;


    public FilmFragment() {
        // Required empty public constructor
    }


    @Override
    protected BasePresenter initPresenter() {
        return new PresenterImpl();
    }

    @Override
    protected void setListener() {

    }

    @Override
    public int getFragLayoutID() {
        return R.layout.fragment_film;
    }

    @Override
    public void initViews(View view) {

        hot = view.findViewById(R.id.hot);
        coming = view.findViewById(R.id.coming);
        popular = view.findViewById(R.id.popular);

        hot_showing = view.findViewById(R.id.hot_showing);
        coming_soon = view.findViewById(R.id.coming_soon);
        h_find_hot_movie = view.findViewById(R.id.h_find_hot_movie);

        mXBanner= view.findViewById(R.id.home_banner);

    }

    @Override
    public void startCoding() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map_hh = new HashMap<>();
        map_hh.put("userId", "13960");
        map_hh.put("sessionId", "159041049038513960");
        map.put("page", "1");
        map.put("count", "10");
       //  mPresenter.startRequest(Api.BANNER, Banner.class);
        mPresenter.startRequest(Api.FINDCOMINGSOONMOVIELIST, FindReleaseMovieList.class, map_hh, map);

        mPresenter.startRequest(Api.FINDRELEASEMOVIELIST, FindComingSoonMovieList.class, map_hh, map);

        mPresenter.startRequest(Api.FINDHOTMOVIELIST, FindHotMovieList.class, map);

    }

    @Override
    public void lazyLoad() {

    }


    @Override
    public void onSuccess(Object o) {

        //添加轮播图片数据（图片数据不局限于网络图片、本地资源文件、View 都可以）,刷新数据也是调用该方法

        if (o instanceof Banner) {

            Toast.makeText(getContext(), "FindComingSoonMovieList" + ((Banner) o).getMessage(), Toast.LENGTH_SHORT).show();

        }


        if (o instanceof FindComingSoonMovieList) {
            if ("0000".equals(((FindComingSoonMovieList) o).getStatus())) {
                Toast.makeText(getContext(), "FindComingSoonMovieList" + ((FindComingSoonMovieList) o).getMessage(), Toast.LENGTH_SHORT).show();
                coming.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "s" + ((FindComingSoonMovieList) o).getResult().size(), Toast.LENGTH_SHORT).show();
                coming_soon.setLayoutManager(new LinearLayoutManager(getContext()));
                List<FindComingSoonMovieList.ResultBean> list = ((FindComingSoonMovieList) o).getResult();
                coming_soon.setAdapter(new AdapterfindComingSoonMovie(list));
            } else {
                Toast.makeText(getContext(), "" + ((FindComingSoonMovieList) o).getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
        if (o instanceof FindReleaseMovieList) {
            if ("0000".equals(((FindReleaseMovieList) o).getStatus())) {
                Toast.makeText(getContext(), "FindReleaseMovieList" + ((FindReleaseMovieList) o).getMessage(), Toast.LENGTH_SHORT).show();
                popular.setVisibility(View.VISIBLE);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(RecyclerView.HORIZONTAL);
                h_find_hot_movie.setLayoutManager(layoutManager);
                h_find_hot_movie.setAdapter(new AdapterFindReleaseMovie(((FindReleaseMovieList) o).getResult()));
            } else {

            }

        }
        if (o instanceof FindHotMovieList) {
            if ("0000".equals(((FindHotMovieList) o).getStatus())) {
                Toast.makeText(getContext(), "FindHotMovieList" + ((FindHotMovieList) o).getMessage(), Toast.LENGTH_SHORT).show();
                hot_showing.setLayoutManager(new LinearLayoutManager(getContext()));
                hot.setVisibility(View.VISIBLE);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(RecyclerView.HORIZONTAL);
                hot_showing.setLayoutManager(layoutManager);
                hot_showing.setAdapter(new AdapterFindHotMovie(((FindHotMovieList) o).getResult()));
            } else {

            }

        }
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), "x" + error, Toast.LENGTH_SHORT).show();
    }
}
