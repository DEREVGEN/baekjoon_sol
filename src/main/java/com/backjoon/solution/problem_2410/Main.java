package com.backjoon.solution.problem_2410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());

        final long MOD = 1000000000;

        long[] DP = new long[N+1];

        DP[1] = 1;
        for (int i = 2; i <= N; i++) {
            if (i % 2 == 1)
                DP[i] = DP[i-1];
            else
                DP[i] = (DP[i-1] + DP[i/2]) % MOD;
        }

        System.out.println(DP[N]);
    }
}
