package com.backjoon.solution.problem_2302;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());

        int M = Integer.parseInt(input.readLine());

        boolean[] vip = new boolean[N+1];

        for (int i = 1; i <= M; i++) {
            int n = Integer.parseInt(input.readLine());
            vip[n] = true;
        }

        int[] dp = new int[N+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            if (vip[i] || vip[i-1])
                dp[i] = dp[i-1];
            else
                dp[i] = dp[i-2] + dp[i-1];
        }

        System.out.println(dp[N]);
    }
}