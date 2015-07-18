package com.insa.rocketlyonandroid.models;

import android.location.Location;

import java.util.ArrayList;

public class Arret {

    private int id;
    private String nom;
    private Location location;
    private ArrayList<Ligne> lignes;

    public Arret() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<Ligne> getLignes() {
        return lignes;
    }

    public void setLignes(ArrayList<Ligne> lignes) {
        this.lignes = lignes;
    }
}
