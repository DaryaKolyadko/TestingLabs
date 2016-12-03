package com.kolyadko.action;

import com.kolyadko.exception.TriangleActionException;
import com.kolyadko.shape.Triangle;
import com.kolyadko.type.TriangleType;

/**
 * Created by DaryaKolyadko on 03.12.2016.
 */
public class TriangleAction {
    public static Double calculateArea(Triangle triangle) throws TriangleActionException {
        if (triangle != null) {
            double halfPer = ((double) calculatePerimeter(triangle)) / 2;
            return Math.sqrt(halfPer * (halfPer - triangle.getA()) * (halfPer - triangle.getB()) * (halfPer - triangle.getC()));
        } else {
            throw new TriangleActionException("Triangle is null");
        }
    }

    public static Integer calculatePerimeter(Triangle triangle) throws TriangleActionException {
        if (triangle != null) {
            return triangle.getA() + triangle.getB() + triangle.getC();
        } else {
            throw new TriangleActionException("Triangle is null");
        }
    }

    public static TriangleType determineTriangleType(Triangle triangle) throws TriangleActionException {
        if (triangle != null) {
            if (triangle.getA() == triangle.getB()) {
                if (triangle.getB() == triangle.getC()) {
                    return TriangleType.EQUILATERAL;
                } else {
                    return TriangleType.ISOSCELES;
                }
            } else if (triangle.getB() == triangle.getC()) {
                return TriangleType.ISOSCELES;
            } else if (triangle.getA() == triangle.getC()) {
                return TriangleType.ISOSCELES;
            } else {
                return TriangleType.SCALENE;
            }
        } else {
            throw new TriangleActionException("Triangle is null.");
        }
    }
}