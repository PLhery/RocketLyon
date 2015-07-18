package com.insa.rocketlyonandroid.controller;

import android.location.Location;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.insa.rocketlyon.R;
import com.insa.rocketlyonandroid.models.Arret;
import com.insa.rocketlyonandroid.utils.Utils;
import com.insa.rocketlyonandroid.view.HomeFragment;
import com.insa.rocketlyonandroid.view.MainActivity;

import java.sql.Connection;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

// Sous-classe d'un adapter classique, qui remplit un layout de feeditems,
// à partir d'une liste de FeedItem (classe FeedItem => utile)

public class ArretsListAdapter extends RecyclerView.Adapter<ArretsListAdapter.ViewHolder> {
    private MainActivity activity;
    private HomeFragment fragment;
    private ArrayList<Arret> arrets;

    public ArretsListAdapter(MainActivity activity, HomeFragment fragment, ArrayList<Arret> arrets) {
        this.activity = activity;
        this.fragment = fragment;
        this.arrets = arrets;
    }

    /* Refreshes the newsfeed in a proper way */
    public void refresh() {
        notifyDataSetChanged();
    }

    /* Assigns every view from feeditem.xml to a view in NewsfeedAdapter */
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.arret_title) TextView arretTitle;
        @Bind(R.id.arret_distance) TextView arretDistance;
        @Bind(R.id.arret_favorite) ImageView arretFavorite;
        @Bind(R.id.list_lignes) ListView lignesList;
        @Bind(R.id.button_timetable) Button buttonTimetable;
        @Bind(R.id.button_map) Button buttonMap;
        @Bind(R.id.button_streetview) Button buttonStreetview;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
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

        String distance = calculateDistance(arret.getLocation());
        holder.arretDistance.setText(distance);

        holder.lignesList.setAdapter(new LignesListAdapter(activity, arret.getLignes()));

        holder.buttonMap.setTag(position);
        holder.buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.openMap(arret);
            }
        });

        holder.buttonTimetable.setTag(position);
        holder.buttonTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.openTimetable(arret);
            }
        });

        holder.buttonStreetview.setTag(position);
        holder.buttonStreetview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.openStreetview(arret);
            }
        });
    }

    private String calculateDistance(Location location) {
        return String.valueOf(Utils.randInt(10, 100)) + "," + String.valueOf(Utils.randInt(1,999)) + "m";
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return arrets.size();
    }

}
