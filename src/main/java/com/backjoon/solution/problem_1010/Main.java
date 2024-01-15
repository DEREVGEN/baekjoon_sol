package com.backjoon.solution.problem_1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] dp;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {

            st = new StringTokenizer(input.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            dp = new int[N + 1][M + 1];
            for (int i = 1; i <= N; i++)
                Arrays.fill(dp[i], -1);

            for (int i = 1; i <= M - N + 1; i++) {
                dp[N][M - i + 1] = i;
            }

            int res = find(1, 1);
            sb.append(res).append('\n');
        }

        System.out.println(sb);
    }

    public static int find(int left, int right) {
        if (dp[left][right] != -1)
            return dp[left][right];

        int sum = 0;

        for (int r = right; r <= M - N + left; r++) {
            sum += find(left + 1, r+1);
        }

        return dp[left][right] = sum;
    }
}