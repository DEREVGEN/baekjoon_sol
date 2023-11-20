package com.backjoon.solution.problem_2293;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] dp;
    static int[] coin;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[K+1];
        coin = new int[N];

        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(input.readLine());
        }
        Arrays.sort(coin);

        dp[0] = 1;

        for (int c = 0; c < N; c++) {
            for (int k = coin[c]; k <= K; k++) {
                dp[k] += dp[k - coin[c]];
            }
        }

        System.out.println(dp[K]);
    }
}