package com.backjoon.solution.problem_1309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());

        long[] dp = new long[N+1];
        dp[0] = 1;
        dp[1] = 3;

        for (int i = 2; i <= N; i++) {
            dp[i] = (2 * dp[i-1] + dp[i-2]) % 9901;
        }

        System.out.println(dp[N]);
    }
}