package com.bw.movie.view.andapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.band.FindMovieByKeyword;
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
public class AdapterSo extends RecyclerView.Adapter<AdapterSo.ViewHolderFindHotMovie> {


    private List<FindMovieByKeyword.ResultBean> list;

    public AdapterSo(List<FindMovieByKeyword.ResultBean> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderFindHotMovie onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderFindHotMovie( LayoutInflater.from( parent.getContext() ).inflate( R.layout.so, parent, false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFindHotMovie holder, int position) {
        FindMovieByKeyword.ResultBean bean = list.get( position );
        FrescoUtil.getInstance().myProgressive(bean.getImageUrl(), holder.hImage);
        holder.hName.setText(bean.getName());
        holder.hScore.setText(bean.getScore()+"分");

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

            super( itemView );
            ButterKnife.bind( this, itemView );
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
