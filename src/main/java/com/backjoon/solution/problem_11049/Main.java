package com.backjoon.solution.problem_11049;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] dp;
    static int[][] mat;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // dp문제는 [1, N]
        N = Integer.parseInt(input.readLine());
        dp = new int[N+1][N+1];
        for (int i = 1; i <= N; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        mat = new int[N+1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(input.readLine());

            mat[i][0] = Integer.parseInt(st.nextToken());
            mat[i][1] = Integer.parseInt(st.nextToken());

            dp[i][i] = 0;
        }

        int res = findMin(1, N);
        System.out.println(res);

    }

    public static int findMin(int from, int to) {
        if (dp[from][to] != Integer.MAX_VALUE)
            return dp[from][to];

        for (int idx = from; idx < to; idx++) {
            dp[from][to] = Math.min(dp[from][to], findMin(from, idx) +
                    findMin(idx+1, to) + mat[from][0] * mat[idx+1][0] * mat[to][1]);
        }

        return dp[from][to];
    }


}