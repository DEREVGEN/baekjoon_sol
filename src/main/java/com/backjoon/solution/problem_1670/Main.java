package com.backjoon.solution.problem_1670;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        long[] dp = new long[N+1];

        dp[0] = 1;
        dp[2] = 1;

        final long MOD = 987654321;

        for (int i = 4; i <= N; i += 2) {
            long sum = 0;
            for (int j = 0; j <= i - 2; j += 2) {
                sum = (sum + dp[j] * dp[i-2-j]) % MOD;
            }
            dp[i] = sum;
        }

        System.out.println(dp[N]);
    }
}
