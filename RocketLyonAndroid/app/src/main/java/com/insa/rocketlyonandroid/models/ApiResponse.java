package com.insa.rocketlyonandroid.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ApiResponse extends ArrayList<Arret> {

    @SerializedName("arrets")
    private ArrayList<Arret> arrets;

    public ApiResponse() {

    }
}
