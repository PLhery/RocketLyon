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
import com.insa.rocketlyonandroid.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NearArretsFragment extends ArretsFragment implements ArretsView, RVView {
    @Bind(R.id.recyclerView) RecyclerView rv;
    private ArretsListAdapter arretsListAdapter;

    public NearArretsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Arret> arretsList = Utils.loadArretsFromFile(mActivity);
        orderByDistance(arretsList);
        arretsListAdapter = new ArretsListAdapter((MainActivity) mActivity, this, arretsList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_neararrets, container, false);
        ButterKnife.bind(this, v);
        initRecyclerView();

        return v;
    }

    private void orderByDistance(ArrayList<Arret> arretsList) {
        Collections.sort(arretsList, new Comparator<Arret>() {
            @Override
            public int compare(Arret ar1, Arret ar2) {
                Location l = mGPSService.getLocation();
                float d1 = l.distanceTo(ar1.getLocation());
                float d2 = l.distanceTo(ar2.getLocation());
                if (d1 == d2) return 0;
                else if (d1 < d2) return -1;
                else return 1;
            }
        });
    }

    @Override
    public void initRecyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        rv.setAdapter(arretsListAdapter); // Assigns the recyclerview to its adapter
    }
}
