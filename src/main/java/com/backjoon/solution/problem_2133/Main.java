package com.backjoon.solution.problem_2133;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());

        int[] B = new int[N+1];

        if (N % 2 == 1) {
            System.out.println(0);
            return;
        }

        //초기 값 세팅
        B[0] = 1;
        B[2] = 3;

        for (int i = 2; i <= N; i+=2) {
            B[i] = B[i-2] * B[2];
            for (int j = i - 4; j >= 0; j-= 2) {
                B[i] += B[j]*2;
            }
        }

        System.out.println(B[N]);
    }
}
