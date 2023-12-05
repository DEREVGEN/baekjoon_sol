package com.backjoon.solution.problem_1947;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        if (N == 1) {
            System.out.println(0);
            return;
        }

        long[] dp = new long[N+1];
        dp[1] = 0;
        dp[2] = 1;

        long MOD = 1_000_000_000;

        for (int i = 3; i <= N; i++) {
            dp[i] = (i - 1) * (dp[i - 2] + dp[i - 1]) % MOD;
        }

        System.out.println(dp[N] % MOD);
    }
}