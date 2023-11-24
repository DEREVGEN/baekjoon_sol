package com.backjoon.solution.problem_1890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());
        map = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 1; j <= N; j++) {
                 map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[N+1][N+1];
        dp[1][1] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == N && j == N)
                    break;

                 int m = map[i][j];

                 if (m + i <= N)
                     dp[m+i][j] += dp[i][j];
                 if (m + j <= N)
                     dp[i][m+j] += dp[i][j];
            }
        }

        System.out.println(dp[N][N]);

    }
}