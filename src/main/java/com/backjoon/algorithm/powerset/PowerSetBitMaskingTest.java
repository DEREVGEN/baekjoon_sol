package com.backjoon.algorithm.powerset;

public class PowerSetBitMaskingTest {

    public static void main(String[] args) {
        int n = 3;

        int powN = (int) Math.pow(2, n);

        for (int i = 0; i < powN; i++) {
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) >= 1) {
                    System.out.println("i: " + i + " j: " + (1<<j));
                }
            }
        }
    }
}
