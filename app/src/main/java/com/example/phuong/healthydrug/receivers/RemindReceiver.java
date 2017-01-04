package com.example.phuong.healthydrug.receivers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.phuong.healthydrug.R;
import com.example.phuong.healthydrug.activities.MainActivity_;
import com.example.phuong.healthydrug.activities.RemindNotificationActivity_;
import com.example.phuong.healthydrug.services.RemindService;

/**
 * Created by phuong on 30/12/2016.
 */

public class RemindReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i=new Intent(context.getApplicationContext(),RemindNotificationActivity_.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        Toast.makeText(context, "Remind....", Toast.LENGTH_LONG).show();
    }



}

