package com.aims_offer;

import org.junit.Test;

public class PrintMatrixTest {

    @Test
    public void printMatrix() {
        int[][] array = new int[][]{
                {1}
        };
        System.out.println(new _19_PrintMatrix().printMatrix(array));

        array = new int[][]{
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15}
        };
        System.out.println(new _19_PrintMatrix().printMatrix(array));

        array = new int[][]{
                {1},
                {2},
                {3},
                {4},
                {5}
        };
        System.out.println(new _19_PrintMatrix().printMatrix(array));

        array = new int[][]{
                {1,2},
                {3,4}
        };
        System.out.println(new _19_PrintMatrix().printMatrix(array));

        array = new int[][]{
                {1,2},
                {3,4},
                {5,6},
                {7,8},
                {9,10}
        };
        System.out.println(new _19_PrintMatrix().printMatrix(array));

        array = new int[][]{
                {1,2,3,4,17},
                {5,6,7,8,18},
                {9,10,11,12,19},
                {13,14,15,16,20}
        };
        System.out.println(new _19_PrintMatrix().printMatrix(array));


    }
}
