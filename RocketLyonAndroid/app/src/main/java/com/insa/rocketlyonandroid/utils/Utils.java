package com.insa.rocketlyonandroid.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.insa.rocketlyonandroid.models.ApiResponse;
import com.insa.rocketlyonandroid.models.Arret;
import com.insa.rocketlyonandroid.models.Ligne;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Utils {

    private Utils() {
    }

    // Returns a pseudo-random number between min and max, inclusive.
    public static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static ArrayList<Arret> createFakeData() {
        ArrayList<Arret> arrets = new ArrayList<>();
        ArrayList<Ligne> lignes;
        Ligne ligne;

        for (int i = 0; i < 10; i++) {
            Arret arret = new Arret();
            arret.setNom("Arret" + (i + 1));
            lignes = new ArrayList<>();
            for (int j = 0; j < Utils.randInt(1, 5); j++) {
                ligne = new Ligne();
                ligne.setNom("Ligne" + (i + 1));
                lignes.add(ligne);
            }
            arret.setLignes(lignes);
            arrets.add(arret);
        }

        return arrets;
    }

    public static ArrayList<Arret> loadArretsFromFile(Context ctx) {
        ArrayList<Arret> arretsList = new ArrayList<>();
        String json = null;
        try {
            InputStream is = ctx.getAssets().open("stations.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return parseJson(json);
    }

    public static ArrayList<Arret> parseJson(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, ApiResponse.class).getArrets();
    }
}
