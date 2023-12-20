package com.backjoon.solution.problem_11994;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static List<Integer> barn;
    static long[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new long[N][K+1][N];

        for (int m = 0; m < N; m++) {
            for (int i = 0; i <= K; i++) {
                Arrays.fill(dp[m][i], Integer.MAX_VALUE);
            }
        }

        barn = new ArrayList<>();
        for (int i = 0; i < N; i++)
            barn.add(Integer.parseInt(input.readLine()));


        long min = Long.MAX_VALUE;
        for (int i = 0; i < N; i++) {

            min = Math.min(min, calculate(i, K, 0));
            Collections.rotate(barn, -1);
        }
        System.out.println(min);
    }

    public static long calculate(int r, int k, int s) {
        if (k == 1) {
            return cost(s, N-1);
        }

        if (dp[r][k][s] != Integer.MAX_VALUE)
            return dp[r][k][s];

        int m = N - k - 1;

        for (int i = s; i <= m; i++) {
            dp[r][k][s] = Math.min(dp[r][k][s], cost(s, i) + calculate(r, k-1, i+1));
        }

        return dp[r][k][s];
    }

    public static long cost(int s, int e) {
        long sum = 0;
        int walk = 0;
        for (int i = s; i <= e; i++) {
            sum += (long) walk * barn.get(i);
            walk++;
        }

        return sum;
    }

}