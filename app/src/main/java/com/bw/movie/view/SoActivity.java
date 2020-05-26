package com.bw.movie.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.band.FindMovieByKeyword;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.url.Api;
import com.bw.movie.view.andapter.AdapterSo;

import java.util.HashMap;

public class SoActivity extends BaseActivity {
    private EditText ssk;
    private Button so;
    private RecyclerView rlv;

    @Override
    public BasePresenter initPresenter() {
        return new PresenterImpl();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_so;
    }

    @Override
    public void initViews() {
        ssk = findViewById( R.id.ssk );
        so = findViewById( R.id.so );
        rlv = findViewById( R.id.rlv );
    }

    @Override
    public void setListener() {
        so.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( SoActivity.this, "3", Toast.LENGTH_SHORT ).show();
                HashMap<String, Object> map = new HashMap<>();
                map.put( "keyword", ssk.getText().toString() );
                map.put( "page", "1" );
                map.put( "count", "5" );
                mPresenter.startRequest( Api.FINDMOVIEBYKEYWORD, FindMovieByKeyword.class, map );
            }
        } );
    }

    @Override
    public void startCoding() {
        HashMap<String, Object> map = new HashMap<>();
        map.put( "keyword", "" );
        map.put( "page", "1" );
        map.put( "count", "10" );
        mPresenter.startRequest( Api.FINDMOVIEBYKEYWORD, FindMovieByKeyword.class, map );
    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof FindMovieByKeyword) {
            String status = ((FindMovieByKeyword) o).getStatus();
            if ("0000".equals( status )) {
                rlv.setLayoutManager( new LinearLayoutManager( this ) );
                rlv.setAdapter(new AdapterSo(((FindMovieByKeyword) o).getResult()) );
                Toast.makeText( this, "1" + ((FindMovieByKeyword) o).getResult().size(), Toast.LENGTH_SHORT ).show();
            } else {
                Toast.makeText( this, "2", Toast.LENGTH_SHORT ).show();
            }

        }
    }

    @Override
    public void onError(String error) {

    }


}
