package com.example.phuong.healthydrug.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phuong.healthydrug.R;
import com.example.phuong.healthydrug.listeners.OnClickItemHospitalListener;
import com.example.phuong.healthydrug.models.Hospital;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by phuong on 29/12/2016.
 */

public class HospitalsAdapter  extends RecyclerView.Adapter<HospitalsAdapter.MyViewHolder>{
    private Context mContext;
    private List<Hospital> mHospitals;
    private OnClickItemHospitalListener mListener;

    public HospitalsAdapter(Context mContext, List<Hospital> mHospitals,OnClickItemHospitalListener listener) {
        this.mContext = mContext;
        this.mHospitals = mHospitals;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_all_hospital_hospital_fragment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Hospital hospital = mHospitals.get(position);
        holder.mTvName.setText(hospital.getName());
        holder.mTvAddress.setText(hospital.getName());
        Picasso.with(mContext)
                .load(hospital.getImage())
                .placeholder(R.drawable.image_default)
                .error(R.drawable.image_not_found)
                .into(holder.mImgIcon);
    }

    @Override
    public int getItemCount() {
        return mHospitals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImgIcon;
        public TextView mTvName;
        public TextView mTvAddress;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tvHospitalName);
            mTvAddress = (TextView) itemView.findViewById(R.id.tvAddressOfHospital);
            mImgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.clickItemHospitalListener(getAdapterPosition());
                }
            });
        }
    }
}
