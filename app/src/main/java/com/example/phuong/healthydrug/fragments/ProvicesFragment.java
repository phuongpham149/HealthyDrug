package com.example.phuong.healthydrug.fragments;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phuong.healthydrug.R;
import com.example.phuong.healthydrug.adapters.ProvicesAdapter;
import com.example.phuong.healthydrug.listeners.OnClickItemProviceListener;
import com.example.phuong.healthydrug.models.Provices;
import com.orm.SugarContext;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuong on 28/12/2016.
 */
@EFragment(R.layout.provices_fragment)
public class ProvicesFragment extends BaseFragment implements OnClickItemProviceListener{
    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private ProvicesAdapter mProvicesAdapter;
    private List<Provices> mProvices;

    @Override
    public void inits() {
        SugarContext.init(getActivity());
        initData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mProvicesAdapter = new ProvicesAdapter(mProvices, getContext(),this);
        mRecyclerView.setAdapter(mProvicesAdapter);
    }


    public void initData() {
        mProvices = new ArrayList<>();
        //mProvices = Provices.listAll(Provices.class);
        mProvices.add(new Provices("Hà Nội","https://upload.wikimedia.org/wikipedia/vi/thumb/1/13/Hanoi_Logo.svg/1021px-Hanoi_Logo.svg.png"));
        mProvices.add(new Provices("Hải Phòng","https://upload.wikimedia.org/wikipedia/vi/thumb/1/13/Hanoi_Logo.svg/1021px-Hanoi_Logo.svg.png"));
        mProvices.add(new Provices("Bắc Giang","https://upload.wikimedia.org/wikipedia/vi/thumb/1/13/Hanoi_Logo.svg/1021px-Hanoi_Logo.svg.png"));

    }

    @Override
    public void clickItemProviceListener(int position) {
        HospitalsFragment hospitalsFragment = HospitalsFragment_.builder().build();
        //hospitalsFragment.mIdProvice = mProvices.get(position).getId();
        //hospitalsFragment.mIdProvice = 2;
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.rootView, hospitalsFragment).addToBackStack(getActivity().getClass().getName()).commit();


    }
}
