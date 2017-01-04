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
    public static final String NAME_SHAREPREFERENCES = "remind";
    public static final String HOUR_SHAREPREFERENCES = "hour";
    public static final String MIN_SHAREPREFERENCES = "min";
    public static final String STATUS_SHAREPREFERENCES = "status";
    @ViewById(R.id.switchTurnAlarm)
    Switch mBtnTurnAlarm;
    @ViewById(R.id.rlSetTime)
    RelativeLayout mRlSetTime;
    @ViewById(R.id.tvHour)
    TextView mTvHour;
    @ViewById(R.id.tvMin)
    TextView mTvMin;
    private Calendar mCurrentTime;
    private String mHourSelect = "";
    private String mMinSelect = "";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mStatus = "";


    @Override
    public void inits() {
        mCurrentTime = Calendar.getInstance();

        mSharedPreferences = getActivity().getSharedPreferences(NAME_SHAREPREFERENCES, 0);
        mEditor = mSharedPreferences.edit();
        if (mSharedPreferences.getString(HOUR_SHAREPREFERENCES, "").length() > 0) {

            mTvHour.setText(mSharedPreferences.getString(HOUR_SHAREPREFERENCES, ""));
            mTvMin.setText(mSharedPreferences.getString(MIN_SHAREPREFERENCES, ""));
        } else {
            mTvHour.setText(String.valueOf(mCurrentTime.get(Calendar.HOUR_OF_DAY)));
            mTvMin.setText(String.valueOf(mCurrentTime.get(Calendar.MINUTE)));
        }

        if (mSharedPreferences.getBoolean(STATUS_SHAREPREFERENCES, false)) {
            mBtnTurnAlarm.setChecked(true);
        }
        BusProvider.getInstance().register(this);
    }

    @Click(R.id.rlSetTime)
    void setTimeAction() {
        int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                mHourSelect = String.valueOf(selectedHour);
                mMinSelect = String.valueOf(selectedMinute);
                mTvHour.setText(mHourSelect);
                mTvMin.setText(mMinSelect);
                mEditor.putString(HOUR_SHAREPREFERENCES, mHourSelect);
                mEditor.putString(MIN_SHAREPREFERENCES, mMinSelect);
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
    void changeState(CompoundButton hello, boolean turnOn) {
        if (turnOn) {
            Log.d("tagstart","true11111");
            mEditor.putBoolean(STATUS_SHAREPREFERENCES, true);
            mEditor.putString(HOUR_SHAREPREFERENCES, mTvHour.getText().toString());
            mEditor.putString(MIN_SHAREPREFERENCES, mTvMin.getText().toString());
            mStatus = "true";
            BusProvider.getInstance().post(mStatus);
        } else {
            Log.d("tagstart","true00000");
            mEditor.putBoolean(STATUS_SHAREPREFERENCES, false);
            mEditor.putString(HOUR_SHAREPREFERENCES, mTvHour.getText().toString());
            mEditor.putString(MIN_SHAREPREFERENCES, mTvMin.getText().toString());
            mStatus = "false";
            BusProvider.getInstance().post(mStatus);
        }
        mEditor.commit();

    }
}
