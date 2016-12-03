package com.kolyadko.action;

import com.kolyadko.exception.TriangleActionException;
import com.kolyadko.exception.TriangleBuilderException;
import com.kolyadko.shape.Triangle;
import com.kolyadko.shape.TriangleBuilder;
import com.kolyadko.type.TriangleType;
import com.kolyadko.util.DoubleComparator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by DaryaKolyadko on 03.12.2016.
 */
public class TriangleActionTest {
    private Triangle[] correctTriangles;

    @DataProvider(name = "wrongData")
    public Object[][] wrongData() {
        return new Object[][]{
                {null},
        };
    }

    @DataProvider(name = "correctDataPerimeter")
    public Object[][] correctDataPerimeter() {
        return new Object[][]{
                {correctTriangles[0], 12},
                {correctTriangles[1], 35},
                {correctTriangles[2], 35},
                {correctTriangles[3], 35},
                {correctTriangles[4], 15},
        };
    }

    @DataProvider(name = "correctDataArea")
    public Object[][] correctDataArea() {
        return new Object[][]{
                {correctTriangles[0], 6.928203},
                {correctTriangles[1], 49.607837},
                {correctTriangles[2], 49.607837},
                {correctTriangles[3], 49.607837},
                {correctTriangles[4], 9.921567},
        };
    }

    @DataProvider(name = "correctDataTriangleType")
    public Object[][] correctDataTriangleType() {
        return new Object[][]{
                {correctTriangles[0], TriangleType.EQUILATERAL},
                {correctTriangles[1], TriangleType.ISOSCELES},
                {correctTriangles[2], TriangleType.ISOSCELES},
                {correctTriangles[3], TriangleType.ISOSCELES},
                {correctTriangles[4], TriangleType.SCALENE},
        };
    }

    @BeforeClass
    public void initDataProvider() throws TriangleBuilderException {
        correctTriangles = new Triangle[]{
                TriangleBuilder.build(4, 4, 4),
                TriangleBuilder.build(10, 10, 15),
                TriangleBuilder.build(10, 15, 10),
                TriangleBuilder.build(15, 10, 10),
                TriangleBuilder.build(4, 5, 6)};
    }

    @Test(dataProvider = "correctDataArea")
    public void testCalculateArea(Triangle triangle, Double result) throws Exception {
        assertTrue(DoubleComparator.equals(TriangleAction.calculateArea(triangle), result));
    }

    @Test (dataProvider = "correctDataPerimeter")
    public void testCalculatePerimeter(Triangle triangle, Integer result) throws Exception {
        assertEquals(TriangleAction.calculatePerimeter(triangle), result);
    }

    @Test (dataProvider = "correctDataTriangleType")
    public void testDetermineTriangleType(Triangle triangle, TriangleType result) throws Exception {
        assertEquals(TriangleAction.determineTriangleType(triangle), result);
    }

    @Test(dataProvider = "wrongData", expectedExceptions = TriangleActionException.class)
    public void testNegativeCalculateArea(Triangle triangle) throws Exception {
        TriangleAction.calculateArea(triangle);
    }

    @Test(dataProvider = "wrongData", expectedExceptions = TriangleActionException.class)
    public void testNegativeCalculatePerimeter(Triangle triangle) throws Exception {
        TriangleAction.calculatePerimeter(triangle);
    }

    @Test (dataProvider = "wrongData", expectedExceptions = TriangleActionException.class)
    public void testNegativeDetermineTriangleType(Triangle triangle) throws Exception {
        TriangleAction.determineTriangleType(triangle);
    }
}