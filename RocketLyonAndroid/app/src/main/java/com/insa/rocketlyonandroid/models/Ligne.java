package com.insa.rocketlyonandroid.models;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

public class Ligne {
    @SerializedName("lineId")
    private String lineId;
    @SerializedName("ligne")
    private String ligne;
    @SerializedName("stopId")
    private String stopId;
    @SerializedName("direction")
    private String direction;
    @SerializedName("mode")
    private int mode;
    @SerializedName("lat")
    private double latitude;
    @SerializedName("long")
    private double longitude;
    private String nom;

    public Ligne() {
    }

    public Type getType() {
        switch (mode) {
            case 1:
                return Type.TRAMWAY;
            case 2:
                return Type.BUS;
            case 3:
                return Type.METRO;
            case 4:
                return Type.FUNICULAIRE;
            default:
                return null;
        }
    }

    public Location getLocation() {
        Location l = new Location("");
        l.setLatitude(latitude);
        l.setLongitude(longitude);

        return l;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private enum Type {
        TRAMWAY, METRO, FUNICULAIRE, BUS
    }
}