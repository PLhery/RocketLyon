package com.insa.rocketlyonandroid.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ApiResponse {

    @SerializedName("arrets")
    private ArrayList<Arret> arrets;

    public ApiResponse() {

    }

    public ArrayList<Arret> getArrets() {
        return arrets;
    }
}
