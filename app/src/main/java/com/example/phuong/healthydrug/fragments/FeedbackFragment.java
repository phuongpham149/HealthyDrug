package com.example.phuong.healthydrug.fragments;

import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phuong.healthydrug.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by phuong on 28/12/2016.
 */
@EFragment(R.layout.feedback_fragment)
public class FeedbackFragment extends BaseFragment {
    @ViewById(R.id.edtName)
    EditText mEdtName;
    @ViewById(R.id.edtEmail)
    EditText mEdtEmail;
    @ViewById(R.id.edtContentFeedBack)
    EditText mEdtContentFeedBack;
    @ViewById(R.id.btnGui)
    Button mBtnSend;
    @ViewById(R.id.btnHuy)
    Button mBtnCancel;

    @Override
    public void inits() {

    }

    @Click(R.id.btnHuy)
    void CancelAction(){
        getActivity().finish();
    }

    @Click(R.id.btnGui)
    void SendAction(){
        String phoneNumber = getResources().getString(R.string.number_phone_receive_feedback);
        StringBuilder msg = new StringBuilder();
        msg.append("From : ");
        msg.append(mEdtName.getText());
        msg.append("\n Email: ");
        msg.append(mEdtEmail.getText());
        msg.append("\n Feedback: ");
        msg.append(mEdtContentFeedBack.getText());

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, msg.toString(), null, null);
        Toast.makeText(getActivity().getApplication(),getResources().getString(R.string.message_send_feedback_success),Toast.LENGTH_SHORT).show();
    }
}
