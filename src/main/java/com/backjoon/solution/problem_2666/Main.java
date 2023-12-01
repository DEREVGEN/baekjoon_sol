package com.backjoon.solution.problem_2666;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][][] dp;
    static int[] doors;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine()); // 벽장 개수

        st = new StringTokenizer(input.readLine());
        int A = Integer.parseInt(st.nextToken()); // 초기 왼쪽 벽장
        int B = Integer.parseInt(st.nextToken()); // 초기 오른쪽 벽장

        M = Integer.parseInt(input.readLine()); // 열어야할 벽장 개수
        doors = new int[M+1];
        for (int i = 1; i <= M; i++)
            doors[i] = Integer.parseInt(input.readLine());

        dp = new int[M+1][N+1][N+1]; // 벽장 문 DP, [순차적 열어야할 벽장][왼쪽 벽장][오른쪽 벽장]
        for (int i = 0; i <= M; i++) {
            for (int j = 0; j <= N; j++)
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
        }

        int res = moveDoors(A, B, 1);
        System.out.println(res);

    }

    public static int moveDoors(int a, int b, int m) {
        if (m == M+1)
            return 0;

        if (dp[m][a][b] != Integer.MAX_VALUE)
            return dp[m][a][b];

        dp[m][a][b] = Math.min(Math.abs(doors[m] - a) + moveDoors(doors[m], b, m + 1),
                Math.abs(doors[m] - b) + moveDoors(a, doors[m], m + 1));

        return dp[m][a][b];
    }
}