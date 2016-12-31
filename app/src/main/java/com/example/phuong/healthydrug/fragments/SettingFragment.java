package com.example.phuong.healthydrug.fragments;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.phuong.healthydrug.R;
import com.example.phuong.healthydrug.models.Remind;
import com.example.phuong.healthydrug.receivers.bus.BusProvider;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;

/**
 * Created by phuong on 28/12/2016.
 */
@EFragment(R.layout.setting_fragment)
public class SettingFragment extends BaseFragment {
    @ViewById(R.id.switchTurnAlarm)
    Switch mBtnTurnAlarm;
    @ViewById(R.id.rlSetTime)
    RelativeLayout mRlSetTime;
    @ViewById(R.id.tvSetTime)
    TextView mTvSetTime;

    private Calendar mCurrentTime;
    private StringBuilder mTime;
    private String mHourSelect = "";
    private String mMinSelect = "";

    public static final String NAME_SHAREPREFERENCES = "remind";
    public static final String HOUR_SHAREPREFERENCES = "hour";
    public static final String MIN_SHAREPREFERENCES = "min";
    public static final String STATUS_SHAREPREFERENCES = "status";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mStatus = "";


    @Override
    public void inits() {
        mCurrentTime = Calendar.getInstance();
        mTime = new StringBuilder();
        mTime.append(String.valueOf(mCurrentTime.get(Calendar.HOUR_OF_DAY)));
        mTime.append(getResources().getString(R.string.between_mins_and_hour));
        mTime.append(String.valueOf(mCurrentTime.get(Calendar.MINUTE)));
        mTvSetTime.setText(mTime);

        mSharedPreferences = getActivity().getSharedPreferences(NAME_SHAREPREFERENCES, 0);
        mEditor = mSharedPreferences.edit();

        BusProvider.getInstance().register(this);
    }

    @Click(R.id.rlSetTime)
    void setTimeAction(){
        int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                mTvSetTime.setText(selectedHour + ":" + selectedMinute);
                mHourSelect = String.valueOf(selectedHour);
                mMinSelect = String.valueOf(selectedMinute);
                mEditor.putString(HOUR_SHAREPREFERENCES,mHourSelect);
                mEditor.putString(MIN_SHAREPREFERENCES,mMinSelect);
                mEditor.commit();

                //goi broadcast
                Remind remind = new Remind();
                remind.setHour(mHourSelect);
                remind.setMins(mMinSelect);
                BusProvider.getInstance().post(remind);

            }
        }, hour, minute, true);
        mTimePicker.setTitle(getResources().getString(R.string.title_settime));
        mTimePicker.show();
    }

    @CheckedChange(R.id.switchTurnAlarm)
    void changeState(CompoundButton hello, boolean turnOn){
        if(turnOn){
            mEditor.putBoolean(STATUS_SHAREPREFERENCES,true);
            mStatus = "true";

        }
        else{
            mEditor.putBoolean(STATUS_SHAREPREFERENCES,false);
            mStatus = "false";
        }
        mEditor.commit();
        BusProvider.getInstance().post(mStatus);
    }
}
