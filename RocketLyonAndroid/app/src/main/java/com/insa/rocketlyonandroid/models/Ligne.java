package com.insa.rocketlyonandroid.models;

import java.util.ArrayList;

public class Ligne {

    private String code;
    private ArrayList<Arret> arrets;
    private String nom;
    private Sens sens;
    private Type type;

    public Ligne() {
    }

    public String getId() {
        return this.code;
    }

    public void setId(String code) {
        this.code = code;
    }

    public ArrayList<Arret> getArrets() {
        return this.arrets;
    }

    public void setStations(ArrayList<Arret> arrets) {
        this.arrets = arrets;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Sens getSens() {
        return this.sens;
    }

    public void setSens(Sens sens) {
        this.sens = sens;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    private enum Sens {
        ALLER, RETOUR
    }

    private enum Type {
        TRAMWAY, METRO, FUNICULAIRE, BUS
    }
}