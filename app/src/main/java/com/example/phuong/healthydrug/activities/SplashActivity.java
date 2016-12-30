package com.example.phuong.healthydrug.activities;

import android.content.Intent;
import android.os.Handler;

import com.example.phuong.healthydrug.R;

import org.androidannotations.annotations.EActivity;

/**
 * Created by phuong on 28/12/2016.
 */
@EActivity(R.layout.splash_screen)
public class SplashActivity extends BaseActivity {

    @Override
    public void inits() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity_.class);
                startActivity(intent);
            }
        },3000);
    }
}
