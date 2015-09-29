package com.insa.rocketlyonandroid.controller;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.insa.rocketlyon.R;
import com.insa.rocketlyonandroid.models.Ligne;
import com.insa.rocketlyonandroid.utils.Utils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LignesListAdapter extends ArrayAdapter<Ligne> {
    public LignesListAdapter(Context ctx, ArrayList<Ligne> lignes) {
        super(ctx, R.layout.ligne_item, lignes);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        // Get the data item for this position
        Ligne ligne = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder holder;
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.ligne_item, parent, false);
            holder = new ViewHolder(v);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        // Sets username in textview
        if(!TextUtils.isEmpty(ligne.getNom())) {
            holder.ligneTitle.setText(ligne.getNom());
        }

        // Checking for empty status message
        String time = calculateTime(ligne);
        holder.ligneTime.setText(time);

        return v;
    }

    private String calculateTime(Ligne ligne) {
        return String.valueOf(Utils.randInt(0,24)) + ":" + String.valueOf(Utils.randInt(0,60));
    }

    /* Assigns every view from feeditem.xml to a view in NewsfeedAdapter */
    protected static class ViewHolder {
        @Bind(R.id.ligne_icon) ImageView ligneIcon;
        @Bind(R.id.ligne_time) TextView ligneTime;
        @Bind(R.id.ligne_title) TextView ligneTitle;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
