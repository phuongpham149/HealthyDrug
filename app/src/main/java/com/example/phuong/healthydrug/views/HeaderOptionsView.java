package com.example.phuong.healthydrug.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.phuong.healthydrug.R;

/**
 * Created by phuong on 04/01/2017.
 */

public class HeaderOptionsView extends RelativeLayout {
    public headerListener mListener;
    public HeaderOptionsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderOptionsView, 0, 0);
        String mTitle1 = typedArray.getString(R.styleable.HeaderOptionsView_title1);
        String mTitle2 = typedArray.getString(R.styleable.HeaderOptionsView_title2);
        boolean isShowBtnLeft = typedArray.getBoolean(R.styleable.HeaderOptionsView_isShowBtnLeft, false);
        boolean isShowImgLogoLeft = typedArray.getBoolean(R.styleable.HeaderOptionsView_isShowLogoLeft, false);
        boolean isShowImgLogoCenter = typedArray.getBoolean(R.styleable.HeaderOptionsView_isShowLogocenter, false);
        typedArray.recycle();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_header, this, true);

        final ImageView mImvBack = (ImageView) getChildAt(0);
        ImageView mImvLogoLeft = (ImageView) getChildAt(1);
        ImageView mImvLogoCenter = (ImageView) getChildAt(2);
        TextView mTvTitle1 = (TextView) getChildAt(3);
        TextView mTvTitle2 = (TextView) getChildAt(4);

        mTvTitle1.setText(mTitle1);
        mTvTitle2.setText(mTitle2);

        if (!isShowBtnLeft) {
            mImvBack.setVisibility(GONE);
        }

        if (!isShowImgLogoLeft) {
            mImvLogoLeft.setVisibility(GONE);
        }

        if (!isShowImgLogoCenter) {
            mImvLogoCenter.setVisibility(GONE);
        }


        mImvBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.backAction();
            }
        });

    }

    public HeaderOptionsView(Context context) {
        super(context, null);
    }

    public void setCallListener(headerListener listener) {
        this.mListener = listener;
    }

    public interface headerListener {
        void backAction();
    }
}
