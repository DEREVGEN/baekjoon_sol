package com.backjoon.solution.problem_2315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static long[][][] dp; // [왼쪽가로등][오른쪽가로등][왼쪽 start or 오른쪽 start]
    static int[] locate;
    static int[] cost;
    static long[] vsum;

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new long[2][N+1][N+1];

        for (int i = 0; i < 2; i++) {
            for (int j = 1; j <= N; j++)
                Arrays.fill(dp[i][j], -1);
        }

        locate = new int[N+1];
        cost = new int[N+1];
        vsum = new long[N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(input.readLine());

            locate[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());

            vsum[i] = vsum[i-1] + cost[i]; // 비용 누적
        }

        System.out.println(turnOff(M, M, 0));
    }

    public static long turnOff(int i, int j, int d) {
        // i: 왼쪽 가로등 인덱스, j: 오른쪽 가로등 인덱스, d: 0:왼쪽 위치, 1: 오른쪽 위치

        if (i == 1 && j == N) // 범위 초과
            return 0;

        // dp 계산된 값 return
        if (dp[d][i][j] != -1)
            return dp[d][i][j];

        int start = (d == 0) ? i : j; // 시작점 위치 지정
        long on = vsum[N] - vsum[j] + vsum[i-1]; // 비용 누적합 계산

        long res = dp[d][i][j];
        // 왼쪽 이동
        if (i - 1 >= 1) {
            res = turnOff(i - 1, j, 0) + (locate[start] - locate[i - 1]) * on;
        }

        // 오른쪽 이동
        if (j + 1 <= N) {
            long tmp = turnOff(i, j + 1, 1) + (locate[j + 1] - locate[start]) * on;

            if (res == -1 || res > tmp)
                res = tmp;
        }

        return dp[d][i][j] = res;
    }
}
