package com.kolyadko.shape;

import com.kolyadko.exception.TriangleBuilderException;

/**
 * Created by DaryaKolyadko on 03.12.2016.
 */
public class TriangleBuilder {
    private static final int MIN_SIDE = 0;
    private static final int MAX_SIDE = 1000000;

    public static Triangle build(int a, int b, int c) throws TriangleBuilderException {
        if (a > MIN_SIDE && b > MIN_SIDE && c > MIN_SIDE) {
            if (a < MAX_SIDE && b < MAX_SIDE && c < MAX_SIDE) {
                if (a + b > c && b + c > a && a + c > b)
                    return new Triangle(a, b, c);
                else
                    throw new TriangleBuilderException("Check whether the triangle inequality holds.");
            } else
                throw new TriangleBuilderException("Sides must be smaller or equals 1.000.000.");
        } else
            throw new TriangleBuilderException("Sides must be bigger than zero.");
    }
}