package com.example.phuong.healthydrug.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.phuong.healthydrug.fragments.SettingFragment;
import com.example.phuong.healthydrug.models.Remind;
import com.example.phuong.healthydrug.receivers.RemindReceiver;
import com.example.phuong.healthydrug.receivers.bus.BusProvider;
import com.squareup.otto.Subscribe;

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
        //lay du lieu tu share
        mRemind = new Remind();
        mRemind.setHour(mSharedPreferences.getString(SettingFragment.HOUR_SHAREPREFERENCES, ""));
        mRemind.setMins(mSharedPreferences.getString(SettingFragment.MIN_SHAREPREFERENCES, ""));
        mRemind.setStatus(mSharedPreferences.getBoolean(SettingFragment.STATUS_SHAREPREFERENCES, false));
        Log.d("tagstart",mRemind.toString());

    }

    @Subscribe
    public void getMessage(Remind remind) {
        mRemind.setHour(remind.getHour());
        mRemind.setMins(remind.getMins());
        Log.d("tag111",mRemind.toString());
        Log.d("tag", "tag1222");
        accessRemind(mRemind);
    }

    @Subscribe
    public void getMessage(String status) {
        if(status.equals("true")){
            mRemind.setStatus(true);
        }
        else{
            mRemind.setStatus(false);
        }
        Log.d("tag", "tag1333 "+mRemind.toString()+" fhfjfj status"+status);
        accessRemind(mRemind);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //viet ham xu ly
        accessRemind(mRemind);
        return START_STICKY;
    }

    public void accessRemind(Remind remind) {
        //time hien tai
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        Log.d("tag","1111111");
        int minsNow = minute + hour * 60;
        int minsAlarm = 0;
        if (("true").equals(String.valueOf(remind.isStatus()))) {
            Log.d("tag11","11222");
            minsAlarm = Integer.parseInt(remind.getMins()) + 60 * (Integer.parseInt(remind.getHour()));
            if (minsAlarm > minsNow) {
                Log.d("tag111",(minsAlarm-minsNow)+"");
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(remind.getHour()));
                calendar.set(Calendar.MINUTE, Integer.parseInt(remind.getMins()));
                Intent myIntent = new Intent(RemindService.this, RemindReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);
                alarmManager = (AlarmManager) this.getSystemService(getBaseContext().ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
        }
    }
}
