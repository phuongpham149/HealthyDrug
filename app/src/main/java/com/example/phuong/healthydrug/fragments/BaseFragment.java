package com.example.phuong.healthydrug.fragments;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * Created by phuong on 28/12/2016.
 */
@EFragment
public abstract class BaseFragment extends Fragment {
    @AfterViews
    public abstract void inits();
}
