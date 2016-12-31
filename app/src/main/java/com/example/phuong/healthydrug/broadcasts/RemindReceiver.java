package com.example.phuong.healthydrug.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by phuong on 30/12/2016.
 */

public class RemindReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
         Log.d("tag","here");
    }
}
