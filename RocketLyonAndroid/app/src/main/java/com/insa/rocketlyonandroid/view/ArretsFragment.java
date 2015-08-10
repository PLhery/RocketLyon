package com.insa.rocketlyonandroid.view;

import android.os.Bundle;

import com.insa.rocketlyonandroid.models.Arret;
import com.insa.rocketlyonandroid.services.GPSTracker;
import com.insa.rocketlyonandroid.utils.BaseFragment;

import trikita.log.Log;

public abstract class ArretsFragment extends BaseFragment implements RVView, ArretsView {
    public static GPSTracker mGPSService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGPSService = new GPSTracker(mActivity);
        updateLocation();
    }

    public void updateLocation() {
        mGPSService.updateLocation();

        if (!mGPSService.isLocationAvailable()) {
            Log.d("GPS not available.");
        }
        mGPSService.closeGPS();
    }

    @Override
    public void openMap(Arret arret) {
        Log.d("openMap");
    }

    @Override
    public void openTimetable(Arret arret) {
        Log.d("openTimetable");
    }

    @Override
    public void openStreetview(Arret arret) {
        Log.d("openStreetview");
    }
}
