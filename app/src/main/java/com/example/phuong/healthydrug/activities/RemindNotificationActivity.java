package com.example.phuong.healthydrug.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.phuong.healthydrug.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by phuong on 02/01/2017.
 */
@EActivity(R.layout.activity_remind_notification)
public class RemindNotificationActivity extends AppCompatActivity {
    private MediaPlayer mMp3;
    @AfterViews
    void inits(){
        PushNotification(this);
        mMp3= MediaPlayer.create(this, R.raw.quehuongtoi);
        mMp3.start();
        /*Intent intent = new Intent(this,MainActivity_.class);
        startActivity(intent);*/

    }

    public void PushNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getResources().getString(R.string.dialog_title_confirm_remind))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
                .setOngoing(true);
        builder.setVibrate(new long[]{1000, 1000});

        Intent notifyIntent =
                new Intent(this, RemindNotificationActivity_.class);

        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        builder.setContentIntent(notifyPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, builder.build());
    }
}
