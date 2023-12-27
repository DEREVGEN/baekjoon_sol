package com.backjoon.solution.problem_9084;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] coin;
    static int money;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {

            N = Integer.parseInt(input.readLine());
            coin = new int[N + 1];

            st = new StringTokenizer(input.readLine());
            for (int i = 1; i <= N; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }
            money = Integer.parseInt(input.readLine());

            int[] dp = new int[money + 1];


            dp[0] = 1;

            for (int i = 1; i <= N; i++) {
                for (int j = coin[i]; j <= money; j++) {
                    dp[j] += dp[j - coin[i]];
                }
            }

            sb.append(dp[money]).append('\n');
        }

        System.out.println(sb);
    }
}