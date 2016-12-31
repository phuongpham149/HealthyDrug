package com.example.phuong.healthydrug.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.phuong.healthydrug.receivers.RemindReceiver;
import com.example.phuong.healthydrug.fragments.SettingFragment;
import com.example.phuong.healthydrug.models.Remind;
import com.example.phuong.healthydrug.receivers.bus.BusProvider;
import com.google.common.eventbus.Subscribe;
import com.squareup.otto.Bus;

import java.util.Calendar;

/**
 * Created by phuong on 30/12/2016.
 */

public class RemindService extends Service {
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Remind mRemind;
    private SharedPreferences mSharedPreferences;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //khoi tao sharePreference
        BusProvider.getInstance().register(this);
        mSharedPreferences = getSharedPreferences(SettingFragment.NAME_SHAREPREFERENCES, 0);
        mRemind = new Remind();

    }

    @Subscribe
    public void getMessage(Remind remind){
        mRemind.setHour(remind.getHour());
        mRemind.setMins(remind.getMins());
        Log.d("tag","tag1222");
        accessRemind(mRemind);
    }

    @Subscribe
    public void getMessage(String status){
        mRemind.setStatus(Boolean.getBoolean(status));
        Log.d("tag","tag1333");
        accessRemind(mRemind);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //lay du lieu tu share
        mRemind.setHour(mSharedPreferences.getString(SettingFragment.HOUR_SHAREPREFERENCES,""));
        mRemind.setMins(mSharedPreferences.getString(SettingFragment.MIN_SHAREPREFERENCES,""));
        mRemind.setStatus(mSharedPreferences.getBoolean(SettingFragment.STATUS_SHAREPREFERENCES,false));
        //viet ham xu ly
        accessRemind(mRemind);
        return START_STICKY;
    }

    public void accessRemind(Remind remind) {
        //time hien tai
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        int minsNow = minute + hour * 60;
        int minsAlarm = 0;
        if (("true").equals(String.valueOf(remind.isStatus()))) {
            minsAlarm = Integer.parseInt(remind.getMins()) + 60 * (Integer.parseInt(remind.getHour()));
            if (minsAlarm > minsNow) {
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(remind.getHour()));
                calendar.set(Calendar.MINUTE, Integer.parseInt(remind.getMins()));
                Intent myIntent = new Intent(RemindService.this, RemindReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(this,0, myIntent, 0);
                alarmManager = (AlarmManager) this.getSystemService(getBaseContext().ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
        }
    }
}
