package com.backjoon.solution.problem_9461;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(input.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= T; i++) {
            int N = Integer.parseInt(input.readLine());

            long[] dp = new long[101];
            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 1;
            dp[3] = 1;
            dp[4] = 2;

            for (int j = 5; j <= N; j++)
                dp[j] = dp[j-5] + dp[j-1];

            sb.append(dp[N]).append('\n');
        }

        System.out.println(sb);
    }
}