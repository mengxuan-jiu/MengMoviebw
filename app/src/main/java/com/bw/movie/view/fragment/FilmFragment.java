package com.bw.movie.view.fragment;


import android.content.Intent;
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
import com.bw.movie.view.SoActivity;
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
    private ImageView soso;


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

        hot = view.findViewById( R.id.hot );
        coming = view.findViewById( R.id.coming );
        popular = view.findViewById( R.id.popular );

        hot_showing = view.findViewById( R.id.hot_showing );
        coming_soon = view.findViewById( R.id.coming_soon );
        h_find_hot_movie = view.findViewById( R.id.h_find_hot_movie );

        mXBanner = view.findViewById( R.id.home_banner );
        soso = view.findViewById( R.id.soso );

    }

    @Override
    public void startCoding() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map_hh = new HashMap<>();
        map_hh.put( "userId", "13960" );
        map_hh.put( "sessionId", "159041049038513960" );
        map.put( "page", "1" );
        map.put( "count", "10" );
        mPresenter.startRequest( Api.BANNER, Banner.class );
        mPresenter.startRequest( Api.FINDCOMINGSOONMOVIELIST, FindReleaseMovieList.class, map_hh, map );

        mPresenter.startRequest( Api.FINDRELEASEMOVIELIST, FindComingSoonMovieList.class, map_hh, map );

        mPresenter.startRequest( Api.FINDHOTMOVIELIST, FindHotMovieList.class, map );
        soso.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SoActivity.class );
                getActivity().startActivity(intent);

            }
        } );
    }

    @Override
    public void lazyLoad() {

    }


    @Override
    public void onSuccess(Object o) {

        //添加轮播图片数据（图片数据不局限于网络图片、本地资源文件、View 都可以）,刷新数据也是调用该方法

        if (o instanceof Banner) {

            Toast.makeText( getContext(), "FindComingSoonMovieList" + ((Banner) o).getMessage(), Toast.LENGTH_SHORT ).show();
            List<Banner.ResultBean> result = ((Banner) o).getResult();
            mXBanner.setBannerData( result );

            mXBanner.loadImage( new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {

                    Glide.with( getActivity() )
                            .load( result.get( position ).getImageUrl() )
                            .into( (ImageView) view );
                }
            } );
        }


        if (o instanceof FindComingSoonMovieList) {
            if ("0000".equals( ((FindComingSoonMovieList) o).getStatus() )) {
                Toast.makeText( getContext(), "FindComingSoonMovieList" + ((FindComingSoonMovieList) o).getMessage(), Toast.LENGTH_SHORT ).show();
                coming.setVisibility( View.VISIBLE );
                Toast.makeText( getContext(), "s" + ((FindComingSoonMovieList) o).getResult().size(), Toast.LENGTH_SHORT ).show();
                coming_soon.setLayoutManager( new LinearLayoutManager( getContext() ) );
                List<FindComingSoonMovieList.ResultBean> list = ((FindComingSoonMovieList) o).getResult();
                AdapterfindComingSoonMovie adapterfindComingSoonMovie = new AdapterfindComingSoonMovie( list );
                coming_soon.setAdapter( adapterfindComingSoonMovie );
                adapterfindComingSoonMovie.setOnitimClickListener( new AdapterfindComingSoonMovie.onitimClickListener() {
                    @Override
                    public void getid(int id) {

                    }
                } );
            } else {
                Toast.makeText( getContext(), "" + ((FindComingSoonMovieList) o).getMessage(), Toast.LENGTH_SHORT ).show();
            }

        }
        if (o instanceof FindReleaseMovieList) {
            if ("0000".equals( ((FindReleaseMovieList) o).getStatus() )) {
                Toast.makeText( getContext(), "FindReleaseMovieList" + ((FindReleaseMovieList) o).getMessage(), Toast.LENGTH_SHORT ).show();
                popular.setVisibility( View.VISIBLE );
                LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
                layoutManager.setOrientation( RecyclerView.HORIZONTAL );
                h_find_hot_movie.setLayoutManager( layoutManager );
                AdapterFindReleaseMovie adapterFindReleaseMovie = new AdapterFindReleaseMovie( ((FindReleaseMovieList) o).getResult() );
                h_find_hot_movie.setAdapter( adapterFindReleaseMovie );

                adapterFindReleaseMovie.setOnitimClickListener( new AdapterFindReleaseMovie.onitimClickListener() {
                    @Override
                    public void getid(int id) {

                    }
                } );
            } else {

            }

        }
        if (o instanceof FindHotMovieList) {
            if ("0000".equals( ((FindHotMovieList) o).getStatus() )) {
                Toast.makeText( getContext(), "FindHotMovieList" + ((FindHotMovieList) o).getMessage(), Toast.LENGTH_SHORT ).show();
                hot_showing.setLayoutManager( new LinearLayoutManager( getContext() ) );
                hot.setVisibility( View.VISIBLE );
                LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
                layoutManager.setOrientation( RecyclerView.HORIZONTAL );
                hot_showing.setLayoutManager( layoutManager );
                AdapterFindHotMovie adapterFindHotMovie = new AdapterFindHotMovie( ((FindHotMovieList) o).getResult() );
                hot_showing.setAdapter( adapterFindHotMovie );
                adapterFindHotMovie.setOnitimClickListener( new AdapterFindHotMovie.onitimClickListener() {
                    @Override
                    public void getid(int id) {

                    }
                } );
            } else {

            }

        }
    }

    @Override
    public void onError(String error) {

    }
}
