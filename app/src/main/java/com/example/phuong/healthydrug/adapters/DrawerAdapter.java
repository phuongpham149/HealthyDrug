package com.example.phuong.healthydrug.adapters;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phuong.healthydrug.R;
import com.example.phuong.healthydrug.listeners.OnClickItemMenuListener;
import com.example.phuong.healthydrug.models.DrawerItem;

import java.util.List;

/**
 * Created by phuong on 28/12/2016.
 */

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.MyViewHolder>{
    private List<DrawerItem> mDrawerItems;
    private Context mContext;
    private OnClickItemMenuListener mListener;

    public DrawerAdapter(List<DrawerItem> mDrawerItems, Context mContext,OnClickItemMenuListener listener) {
        this.mDrawerItems = mDrawerItems;
        this.mContext = mContext;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_menu_drawer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DrawerItem drawerItem = mDrawerItems.get(position);
        holder.mTvTitle.setText(drawerItem.getName());
        holder.mImgIcon.setImageDrawable(mContext.getResources().getDrawable(drawerItem.getIdImage()));
    }

    @Override
    public int getItemCount() {
        return mDrawerItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImgIcon;
        public TextView mTvTitle;


        public MyViewHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tvTitle);

            mImgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.clickItemMenuListener(getAdapterPosition());
                }
            });
        }
    }
}
