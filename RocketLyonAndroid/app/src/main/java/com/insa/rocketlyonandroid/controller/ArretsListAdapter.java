package com.insa.rocketlyonandroid.controller;

import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.insa.rocketlyon.R;
import com.insa.rocketlyonandroid.models.Arret;
import com.insa.rocketlyonandroid.services.GPSTracker;
import com.insa.rocketlyonandroid.view.ArretsView;
import com.insa.rocketlyonandroid.view.MainActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import trikita.log.Log;

// Sous-classe d'un adapter classique, qui remplit un layout de feeditems,
// à partir d'une liste de FeedItem (classe FeedItem => utile)

public class ArretsListAdapter extends RecyclerView.Adapter<ArretsListAdapter.ViewHolder> {
    private MainActivity activity;
    private ArretsView arview;
    private ArrayList<Arret> arrets;

    public ArretsListAdapter(MainActivity activity, ArretsView arview, ArrayList<Arret> arrets) {
        this.activity = activity;
        this.arview = arview;
        this.arrets = arrets;
    }

    // Assigns the feeditem.xml to the viewholder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.arret_item, viewGroup, false);

        return new ViewHolder(v);
    }

    /* Fills view in VH content from Newsfeed */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Arret arret = arrets.get(position);

        String nom = arret.getNom();
        if (!TextUtils.isEmpty(nom)) {
            holder.arretTitle.setText(nom);
        } else {
            holder.arretTitle.setVisibility(View.GONE);
        }

        Location currLocation = GPSTracker.getLocation();
        Log.d(currLocation);
        float distance = GPSTracker.calculateDistance(arret.getLocation(), currLocation);
        holder.arretDistance.setText(String.format("%.2f", distance) + "km");

        holder.lignesList.setAdapter(new LignesListAdapter(activity, arret.getLignes()));

        holder.buttonMap.setTag(position);
        holder.buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arview.openMap(arret);
            }
        });

        holder.buttonTimetable.setTag(position);
        holder.buttonTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arview.openTimetable(arret);
            }
        });

        holder.buttonStreetview.setTag(position);
        holder.buttonStreetview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arview.openStreetview(arret);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return arrets.size();
    }

    /* Assigns every view from feeditem.xml to a view in NewsfeedAdapter */
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.arret_title)
        TextView arretTitle;
        @Bind(R.id.arret_distance)
        TextView arretDistance;
        @Bind(R.id.arret_favorite)
        ImageView arretFavorite;
        @Bind(R.id.list_lignes)
        ListView lignesList;
        @Bind(R.id.button_timetable)
        Button buttonTimetable;
        @Bind(R.id.button_map)
        Button buttonMap;
        @Bind(R.id.button_streetview)
        Button buttonStreetview;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}
