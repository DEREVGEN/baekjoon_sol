package com.backjoon.solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TwoPointerTest {
    public static void main(String[] args) throws IOException {

        int[] num = new int[]{1,2,3,2,5};

        System.out.print("input m: ");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(input.readLine());

        int s = 0, e = 0;
        int subSum = 0;

        while (true) {
            System.out.println("s: " + (s-1) + " e: " + (e-1) + " subSum: " + subSum);
            if (e < num.length && subSum < m) {
                subSum += num[e++];
            } else if(s < num.length) {
                subSum -= num[s++];
            } else {
                break;
            }
        }


    }
}
