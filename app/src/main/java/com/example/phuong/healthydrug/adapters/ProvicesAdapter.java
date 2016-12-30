package com.example.phuong.healthydrug.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phuong.healthydrug.R;
import com.example.phuong.healthydrug.listeners.OnClickItemProviceListener;
import com.example.phuong.healthydrug.models.Provices;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by phuong on 28/12/2016.
 */

public class ProvicesAdapter extends RecyclerView.Adapter<ProvicesAdapter.MyViewHolder> {
    private List<Provices> mProvices;
    private Context mContext;
    private OnClickItemProviceListener mListener;

    public ProvicesAdapter(List<Provices> mProvices, Context mContext, OnClickItemProviceListener listener) {
        this.mProvices = mProvices;
        this.mContext = mContext;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_provices_fragment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Provices provices = mProvices.get(position);
        holder.mTvTitle.setText(provices.getName());
        Picasso.with(mContext)
                .load(provices.getImage())
                .placeholder(R.drawable.image_default)
                .error(R.drawable.image_not_found)
                .into(holder.mImgIcon);
    }

    @Override
    public int getItemCount() {
        return mProvices.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImgIcon;
        public TextView mTvTitle;
        public TextView mTvForward;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            mTvForward = (TextView) itemView.findViewById(R.id.tvForward);
            mImgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);

            mTvForward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.clickItemProviceListener(getAdapterPosition());
                }
            });
        }
    }
}
