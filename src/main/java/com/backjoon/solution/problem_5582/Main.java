package com.backjoon.solution.problem_5582;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String str1 = input.readLine();
        String str2 = input.readLine();

        int N = str1.length();
        int M = str2.length();

        int[][] dp = new int[N+1][M+1];

        int max = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        System.out.println(max);
    }
}