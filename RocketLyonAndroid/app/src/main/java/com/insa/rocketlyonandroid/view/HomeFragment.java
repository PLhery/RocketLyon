package com.insa.rocketlyonandroid.view;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.insa.rocketlyon.R;
import com.insa.rocketlyonandroid.controller.ArretsListAdapter;
import com.insa.rocketlyonandroid.models.Arret;
import com.insa.rocketlyonandroid.services.GPSTracker;
import com.insa.rocketlyonandroid.utils.BaseFragment;
import com.insa.rocketlyonandroid.utils.Utils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import trikita.log.Log;

public class HomeFragment extends BaseFragment implements ArretsView {
    @Bind(R.id.recyclerView) RecyclerView rv;
    private ArretsListAdapter arretsListAdapter;
    private Location location;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNewLocation();

        ArrayList<Arret> arretsList = Utils.loadArretsFromFile(mActivity);
        arretsListAdapter = new ArretsListAdapter((MainActivity) mActivity, this, arretsList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);
        initRecyclerView();

        return v;
    }

    public Location getNewLocation() {
        GPSTracker mGPSService = new GPSTracker(mActivity);
        location = mGPSService.getLocation();

        if (!mGPSService.isLocationAvailable()) {
            // Here you can ask the user to try again, using return; for that
            Log.d("Your location is not available, please try again.");
        }
        mGPSService.closeGPS();
        return location;
    }

    private void initRecyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        rv.setAdapter(arretsListAdapter); // Assigns the recyclerview to its adapter
    }

    public void openMap(Arret arret) {
        Log.d("openMap");
    }

    public void openTimetable(Arret arret) {
        Log.d("openTimetable");
    }

    public void openStreetview(Arret arret) {
        Log.d("openStreetview");
    }

    public Location getLocation() {
        return location;
    }
}
