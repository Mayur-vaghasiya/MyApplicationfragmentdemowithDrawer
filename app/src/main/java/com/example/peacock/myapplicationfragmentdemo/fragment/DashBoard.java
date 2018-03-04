package com.example.peacock.myapplicationfragmentdemo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.peacock.myapplicationfragmentdemo.R;
import com.example.peacock.myapplicationfragmentdemo.interfaces.DrawerLocker;

/**
 * Created by peacock on 18/4/17.
 */

public class DashBoard extends Basefragment implements View.OnClickListener {

    private Activity activity = null;
    private Button btnfragmentone, btnfragmentsecond, btnfragmentthird;

    public DashBoard() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        activity = getBaseActivity();

        ((DrawerLocker) getActivity()).setDrawerEnabled(true);
        //preferenceUtility = getBasePreferenceUtility();

        View dashboard = inflater.inflate(R.layout.dashboard, container, false);


        dashboard.findViewById(R.id.btnfone).setOnClickListener(this);
        dashboard.findViewById(R.id.btntwo).setOnClickListener(this);
        dashboard.findViewById(R.id.btnthree).setOnClickListener(this);

        return dashboard;
    }

    @Override
    public void onResume() {
        super.onResume();

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        getBaseSupportActionBar().setDisplayHomeAsUpEnabled(false);

        setToolBarText().setText(getString(R.string.dashboard));

        //Important Notes: Lock Navigation drawer in this Fragment
        ((DrawerLocker) getActivity()).setDrawerEnabled(true);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnfone:

                onChangeCurrentFragment().onFragmentChangeListener(new fragmentone(), getString(R.string.fargmentones));
                break;
            case R.id.btntwo:
                onChangeCurrentFragment().onFragmentChangeListener(new fragmentsecond(), getString(R.string.fargmenttwos));
                break;
            case R.id.btnthree:
                onChangeCurrentFragment().onFragmentChangeListener(new fragmentthired(), getString(R.string.fargmentthrees));
                break;
            default:
                break;

        }
    }
}