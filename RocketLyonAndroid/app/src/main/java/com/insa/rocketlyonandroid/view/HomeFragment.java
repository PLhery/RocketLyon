package com.insa.rocketlyonandroid.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.insa.rocketlyon.R;
import com.insa.rocketlyonandroid.controller.ArretsListAdapter;
import com.insa.rocketlyonandroid.models.Arret;
import com.insa.rocketlyonandroid.models.Ligne;
import com.insa.rocketlyonandroid.utils.BaseFragment;
import com.insa.rocketlyonandroid.utils.Utils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import trikita.log.Log;

public class HomeFragment extends BaseFragment {
    
    @Bind(R.id.recyclerView) RecyclerView rv;
    private ArretsListAdapter arretsListAdapter;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arretsListAdapter = new ArretsListAdapter((MainActivity) mActivity, this, createFakeData());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);

        initRecyclerView();

        return v;
    }

    private void initRecyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        rv.setAdapter(arretsListAdapter); // Assigns the recyclerview to its adapter
    }

    private ArrayList<Arret> createFakeData() {
        ArrayList<Arret> arrets = new ArrayList<>();
        ArrayList<Ligne> lignes;
        Ligne ligne;

        for (int i=0; i<10; i++) {
            Arret arret = new Arret();
            arret.setNom("Arret" + (i+1));
            lignes = new ArrayList<>();
            for (int j=0; j< Utils.randInt(1,5); j++) {
                ligne = new Ligne();
                ligne.setNom("Ligne" + (i+1));
                lignes.add(ligne);
            }
            arret.setLignes(lignes);
            arrets.add(arret);
        }

        return arrets;
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
}
