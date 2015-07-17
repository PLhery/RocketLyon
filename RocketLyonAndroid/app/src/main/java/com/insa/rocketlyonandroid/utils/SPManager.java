package com.insa.rocketlyonandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

// SharedPreferences class used to deal with local memory,
// makes (saving, loading) calls very easy -> SPManager.save/load()
public class SPManager {

    private SPManager() {
    }

    private static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static boolean load(Context ctx, String flag) {
        return getSharedPreferences(ctx)
                .getBoolean(flag, false);
    }

    public static void save(Context ctx, boolean value, String flag) {
        final SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(flag, value)
                .apply();
    }

    public static void remove(Context ctx, String flag) {
        final SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(flag)
                .apply();
    }

    public static void clear(Context ctx) {
        final SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear()
                .apply();
    }
}