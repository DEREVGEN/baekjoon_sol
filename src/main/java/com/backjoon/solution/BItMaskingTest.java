package com.backjoon.solution;

public class BItMaskingTest {

    public static void main(String[] args) {
        int n = 5;

        System.out.println(1 << n);
        System.out.println(1 << 0);
        System.out.println(1 << 1);
        System.out.println(((5 & 3) == 1 ? true : false));

        for(int i = 0; i < (1 << n); i++)
        {
            long s = 0;
            for(int j = 0; j < n; j++)
                if ((i & (1 << j)) == 1)
                    System.out.println("i: " + i + " j: " + j);
        }
    }
}
