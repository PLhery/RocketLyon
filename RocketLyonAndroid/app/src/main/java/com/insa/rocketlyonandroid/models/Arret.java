package com.insa.rocketlyonandroid.models;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Arret {
    @SerializedName("id")
    private int id;
    @SerializedName("nom")
    private String nom;
    @SerializedName("long")
    private double longitude;
    @SerializedName("lat")
    private double latitude;
    @SerializedName("lignes")
    private ArrayList<Ligne> lignes;

    public Arret() {
    }

    public Location getLocation() {
        Location l = new Location("");
        l.setLatitude(latitude);
        l.setLongitude(longitude);

        return l;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Ligne> getLignes() {
        return lignes;
    }

    public void setLignes(ArrayList<Ligne> lignes) {
        this.lignes = lignes;
    }
}
