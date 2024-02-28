package com.backjoon.solution.problem_12852;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        int[] dp = new int[N+1];
        int[] trace = new int[N+1];

        for(int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            trace[i] = i - 1;

            if(i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
                dp[i] = dp[i / 2] + 1;
                trace[i] = i / 2;
            }

            if(i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
                dp[i] = dp[i / 3] + 1;
                trace[i] = i / 3;
            }
        }

        System.out.println(dp[N]);
        StringBuilder sb = new StringBuilder();
        int idx = N;

        while (idx != 0) {
            sb.append(idx).append(' ');
            idx = trace[idx];
        }

        System.out.println(sb);
    }
}