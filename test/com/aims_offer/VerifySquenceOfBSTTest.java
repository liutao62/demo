package com.aims_offer;

import org.junit.Test;

import static org.junit.Assert.*;

public class VerifySquenceOfBSTTest {

    @Test
    public void verifySquenceOfBST() {
        VerifySquenceOfBST bst = new VerifySquenceOfBST();
        int[] array = new int[]{1,2,3,4,5};
        System.out.println(bst.VerifySquenceOfBST(array));
    }
}