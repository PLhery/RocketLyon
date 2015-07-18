package com.insa.rocketlyonandroid.utils;

import java.util.Random;

public class Utils {

    private Utils() {
    }

    // Returns a pseudo-random number between min and max, inclusive.
    public static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}
