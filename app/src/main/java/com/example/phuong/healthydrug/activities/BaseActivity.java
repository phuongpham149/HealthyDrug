package com.example.phuong.healthydrug.activities;

import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by phuong on 28/12/2016.
 */
@EActivity
public abstract class BaseActivity extends AppCompatActivity {

    @AfterViews
    public abstract void inits();
}
