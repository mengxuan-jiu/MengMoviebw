package com.bw.movie.view.andapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.band.FindHotMovieList;
import com.bw.movie.band.FindReleaseMovieList;
import com.bw.movie.util.FrescoUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author mengxuan
 * @包名 com.bw.movie.mvp.view.adapter
 * @MengXuanmengxuan
 * @日期2020/4/19
 * @项目名Movie
 * @类名AdapterFindHotMovie
 **/
public class AdapterFindHotMovie extends RecyclerView.Adapter<AdapterFindHotMovie.ViewHolderFindHotMovie> {


    private List<FindHotMovieList.ResultBean> list;

    public AdapterFindHotMovie(List<FindHotMovieList.ResultBean> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderFindHotMovie onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderFindHotMovie(LayoutInflater.from(parent.getContext()).inflate(R.layout.find_hot_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFindHotMovie holder, int position) {
        FindHotMovieList.ResultBean bean = list.get(position);
        FrescoUtil.getInstance().myProgressive(bean.getImageUrl(), holder.hImage);
        holder.hName.setText(bean.getName());
        holder.hScore.setText(bean.getScore()+"分");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int movieId = bean.getMovieId();
                onitimClickListener.getid( movieId );
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderFindHotMovie extends RecyclerView.ViewHolder {
        @BindView(R.id.h_image)
        SimpleDraweeView hImage;
        @BindView(R.id.h_name)
        TextView hName;
        @BindView(R.id.h_score)
        TextView hScore;
        @BindView(R.id.butt_ticket_purchase)
        Button buttTicketPurchase;

        public ViewHolderFindHotMovie(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnitimClickListener(AdapterFindHotMovie.onitimClickListener onitimClickListener) {
        this.onitimClickListener = onitimClickListener;
    }

    onitimClickListener onitimClickListener;

    public interface onitimClickListener {
        void getid(int id);
    }


}
