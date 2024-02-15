package com.backjoon.solution.problem_11066;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] nums;
    static int[][] dp;
    static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(input.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(input.readLine());

            nums = new int[N + 1];
            dp = new int[N + 1][N + 1];
            sum = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }

            st = new StringTokenizer(input.readLine());
            for (int i = 1; i <= N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
                sum[i] += sum[i-1] + nums[i];
                dp[i][i] = 0;
                dp[i-1][i] = nums[i] + nums[i-1];
            }

            int res = findMin(1, N);
            sb.append(res).append('\n');
        }

        System.out.println(sb);
    }

    public static int findMin(int from, int to) {
        if (dp[from][to] != Integer.MAX_VALUE)
            return dp[from][to];

        for (int idx = from; idx < to; idx++) {
            dp[from][to] = Math.min(dp[from][to], findMin(from, idx) + findMin(idx + 1, to));
        }

        dp[from][to] += sum[to] - sum[from - 1];

        return dp[from][to];
    }
}