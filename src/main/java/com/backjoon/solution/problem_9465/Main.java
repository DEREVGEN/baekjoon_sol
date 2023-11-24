package com.backjoon.solution.problem_9465;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());
        for (int t = 0; t < T; t++) {

            int N = Integer.parseInt(input.readLine());

            int[][] cards = new int[2][N];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(input.readLine());

                for (int j = 0; j < N; j++) {
                    cards[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (N == 1) {
                System.out.println(Math.max(cards[0][0], cards[1][0]));
                continue;
            }


            int[][] dp = new int[2][N];

            dp[0][0] = cards[0][0];
            dp[1][0] = cards[1][0];
            dp[0][1] = cards[1][0] + cards[0][1];
            dp[1][1] = cards[0][0] + cards[1][1];

            for (int j = 2; j < N; j++) {
                dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + cards[0][j];
                dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + cards[1][j];
            }

            System.out.println(Math.max(dp[0][N - 1], dp[1][N - 1]));
        }

    }
}