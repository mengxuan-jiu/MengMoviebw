package com.bw.movie.view.andapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.band.FindComingSoonMovieList;
import com.bw.movie.util.FrescoUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class AdapterfindComingSoonMovie extends RecyclerView.Adapter<AdapterfindComingSoonMovie.ViewHolderFindHotMovie> {


    private List<FindComingSoonMovieList.ResultBean> list;

    public AdapterfindComingSoonMovie(List<FindComingSoonMovieList.ResultBean> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderFindHotMovie onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderFindHotMovie(LayoutInflater.from(parent.getContext()).inflate(R.layout.find_soon_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFindHotMovie holder, int position) {
        FindComingSoonMovieList.ResultBean bean = list.get(position);
        holder.hName.setText(bean.getName());
        DateFormat format = new SimpleDateFormat("MM月dd日");
        holder.hScore.setText(format.format(bean.getReleaseTime()));
        holder.hRs.setText(bean.getWantSeeNum()+"");
        FrescoUtil.getInstance().myFillet(bean.getImageUrl(),holder.hImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        @BindView(R.id.h_score)
        TextView hScore;
        @BindView(R.id.h_name)
        TextView hName;
        @BindView(R.id.h_rs)
        TextView hRs;
        @BindView(R.id.butt_ticket_purchase)
        Button buttTicketPurchase;

        public ViewHolderFindHotMovie(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    onitimClickListener fOnitimClickListener;

    public void setOnitimClickListener(onitimClickListener onitimClickListener) {
        fOnitimClickListener = onitimClickListener;
    }

    public interface onitimClickListener {

        void getid(int id);
    }


}
