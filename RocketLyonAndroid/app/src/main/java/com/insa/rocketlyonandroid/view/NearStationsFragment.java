package com.insa.rocketlyonandroid.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.insa.rocketlyon.R;
import com.insa.rocketlyonandroid.utils.BaseFragment;

public class NearStationsFragment extends BaseFragment {

    public NearStationsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nearstations, container, false);
    }
}
