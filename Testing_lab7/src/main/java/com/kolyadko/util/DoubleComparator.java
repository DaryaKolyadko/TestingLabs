package com.kolyadko.util;

/**
 * Created by DaryaKolyadko on 03.12.2016.
 */
public class DoubleComparator {
    private static final double ERROR = 0.000001;

    public static boolean equals(double d1, double d2) {
        return Math.abs(d1 - d2) <= ERROR;
    }
}