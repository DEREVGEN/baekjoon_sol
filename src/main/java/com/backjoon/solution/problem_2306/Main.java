package com.backjoon.solution.problem_2306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] dp;
    static String dnaStr;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        dnaStr = input.readLine();
        int N = dnaStr.length();

        dp = new int[N][N];
        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        int res = dfs(0, N-1);
        System.out.println(res);
    }

    public static int dfs(int a, int b) {

        if (a >= b)
            return 0;

        if (dp[a][b] != Integer.MAX_VALUE)
            return dp[a][b];

        int res = -1;
        if ((dnaStr.charAt(a) == 'a' && dnaStr.charAt(b) == 't') ||
                (dnaStr.charAt(a) == 'g' && dnaStr.charAt(b) == 'c')) {
            res = dfs(a+1, b-1) + 2;
        }

        for (int i = a; i < b; i++) {
            res = Math.max(res, dfs(a, i) + dfs(i+1, b));
        }

        return dp[a][b] = res;
    }
}