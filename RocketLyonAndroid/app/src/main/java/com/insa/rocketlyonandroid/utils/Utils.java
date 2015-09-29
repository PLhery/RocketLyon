package com.insa.rocketlyonandroid.utils;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.insa.rocketlyonandroid.models.ApiResponse;
import com.insa.rocketlyonandroid.models.Arret;

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

    public static void showToast(final Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static ArrayList<Arret> loadArretsFromFile(Context ctx) {
        String json;
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
