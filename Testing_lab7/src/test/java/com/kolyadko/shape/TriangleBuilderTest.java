package com.kolyadko.shape;

import com.kolyadko.exception.TriangleBuilderException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by DaryaKolyadko on 03.12.2016.
 */
public class TriangleBuilderTest {
    @DataProvider(name = "wrongDataTriangleInequality")
    public Object[][] wrongDataTriangleInequality() {
        return new Object[][]{
                {12, 3, 6},
                {3, 6, 12},
                {3, 12, 6},
                {4, 9, 5},
                {5, 4, 9},
                {9, 5, 4},
        };
    }

    @DataProvider(name = "wrongDataMaxSideLength")
    public Object[][] wrongDataMaxSideLength() {
        return new Object[][]{
                {1000000, 5, 9},
                {5, 9, 1000000},
                {5, 1000000, 9},
                {1000001, 2, 3},
                {2, 3, 1000001},
                {2, 1000001, 3}
        };
    }

    @DataProvider(name = "wrongDataMinSideLength")
    public Object[][] wrongDataMinSideLength() {
        return new Object[][]{
                {7, 5, 0},
                {0, 7, 5},
                {5, 0, 7},
                {-1, 3, 4},
                {3, 4, -1},
                {4, -1, 3},
                {4, -1, 3},
                {4, -1, 3}
        };
    }

    @DataProvider(name = "correctData")
    public Object[][] correctData() {
        return new Object[][]{
                {4, 4, 4},
                {10, 10, 15},
                {10, 15, 10},
                {15, 10, 10},
                {4, 5, 6}
        };
    }

    @Test(dataProvider = "correctData")
    public void testBuild(int a, int b, int c) throws Exception {
        TriangleBuilder.build(a, b, c);
    }

    @Test(dataProvider = "wrongDataMinSideLength", expectedExceptions = TriangleBuilderException.class,
            expectedExceptionsMessageRegExp = "Sides must be bigger than zero.")
    public void testNegateMinSideLengthBuild(int a, int b, int c) throws Exception {
        TriangleBuilder.build(a, b, c);
    }

    @Test(dataProvider = "wrongDataMaxSideLength", expectedExceptions = TriangleBuilderException.class,
            expectedExceptionsMessageRegExp = "Sides must be smaller or equals 1.000.000.")
    public void testNegateMaxSideLengthBuild(int a, int b, int c) throws Exception {
        TriangleBuilder.build(a, b, c);
    }

    @Test(dataProvider = "wrongDataTriangleInequality", expectedExceptions = TriangleBuilderException.class,
            expectedExceptionsMessageRegExp = "Check whether the triangle inequality holds.")
    public void testNegateTriangleInequalityBuild(int a, int b, int c) throws Exception {
        TriangleBuilder.build(a, b, c);
    }
}