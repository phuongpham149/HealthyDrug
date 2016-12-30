package com.example.phuong.healthydrug.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.phuong.healthydrug.R;
import com.example.phuong.healthydrug.adapters.HospitalsAdapter;
import com.example.phuong.healthydrug.adapters.ProvicesAdapter;
import com.example.phuong.healthydrug.listeners.OnClickItemHospitalListener;
import com.example.phuong.healthydrug.models.Hospital;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuong on 29/12/2016.
 */
@EFragment(R.layout.hospitals_fragment)
public class HospitalsFragment extends BaseFragment implements OnClickItemHospitalListener{
    @FragmentArg
    public long mIdProvice;
    @ViewById(R.id.recyclerViewHospital)
    RecyclerView mRecyclerView;
    private List<Hospital> mHospitals;
    private HospitalsAdapter mHospitalsAdapter;

    @Override
    public void inits() {
        initData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mHospitalsAdapter = new HospitalsAdapter( getContext(),mHospitals,this);
        mRecyclerView.setAdapter(mHospitalsAdapter);
    }

    public void initData(){
        mHospitals = new ArrayList<>();
        Hospital hospital = new Hospital();
        hospital.setName("Bạch Mai");
        hospital.setAddress("78 Đường Giải Phóng, Phương Mai, Đống Đa, Hà Nội ");
        hospital.setImage("http://www.bachmai.vn/UserFiles/Image/tempt.jpg");
        hospital.setPhone("844 3869 3731");
        hospital.setWebsite("bachmaihospital.org");
        mHospitals.add(hospital);
        //mHospitals = Hospital.listAll(Hospital.class," provices = "+mIdProvice);
    }

    @Override
    public void clickItemHospitalListener(int position) {
        HospitalDetailFragment hospitalDetailFragment = HospitalDetailFragment_.builder().build();
        //hospitalDetailFragment.idHospital = mHospitals.get(position).getId();
        hospitalDetailFragment.idHospital =2;
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.rootView, hospitalDetailFragment).addToBackStack(getActivity().getClass().getName()).commit();
    }
}
