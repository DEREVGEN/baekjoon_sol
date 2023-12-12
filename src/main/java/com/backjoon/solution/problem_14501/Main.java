package com.backjoon.solution.problem_14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] T;
    static int[] P;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());

        T = new int[N+1];
        P = new int[N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(input.readLine());

            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            T[i] = t;
            P[i] = p;
        }

        dp = new int[N+2];

        calculate(0, 0);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++)
            max = Math.max(max, dp[i]);
        System.out.println(max);
    }

    public static void calculate(int start, int profit) {

        for (int i = start; i <= N; i++) {

            int next = i + T[i];
            if (next > N+1) continue;
            int nextProfit = profit + P[i];

            if (dp[next] < nextProfit) {
                dp[next] = nextProfit;
                calculate(next, nextProfit);
            }
        }
    }
}