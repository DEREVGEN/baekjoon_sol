package com.backjoon.solution.problem_1513;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, C;
    static int[][][][] dp;
    static int[][] arcades;

    static final int MOD = 1000007;
    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // (최대 방문 오락실 num, 방문횟수, y값, x값)
        dp = new int[C+1][C+1][N+1][M+1];
        // 오락실 위치 - 오락실 num
        arcades = new int[N+1][M+1];

        // dp 초기값
        dp[0][0][1][1] = 1;

        for (int i = 1; i <= C; i++) {
            st = new StringTokenizer(input.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            if (y == 1 && x == 1) {
                dp[0][0][1][1] = 0;
                dp[i][1][1][1] = 1;
            }
            arcades[y][x] = i;
        }

        findPath();

        // i: 방문횟수, j: 오락실넘버, 오락실 넘버에 대한 방문횟수를 모두 구한다.
        for (int i = 0; i <= C; i++) {
            int sum = 0;
            for (int j = 0; j <= C; j++) {
                sum = (sum + dp[j][i][N][M]) % MOD;
            }

            System.out.print(sum + " ");
        }
    }

    public static void findPath() {

        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= M; x++) {

                if (y == 1 && x == 1)
                    continue;

                if (arcades[y][x] > 0) {
                    int limit = arcades[y][x];

                    for (int l = 0; l < limit; l++) {
                        for (int c = 0; c <= l; c++)
                            dp[limit][c+1][y][x] = (dp[limit][c+1][y][x] + dp[l][c][y-1][x] + dp[l][c][y][x-1]) % MOD;
                    }

                } else {
                    for (int l = 0; l <= C; l++) {
                        for (int c = 0; c <= l; c++)
                            dp[l][c][y][x] = (dp[l][c][y-1][x] + dp[l][c][y][x-1]) % MOD;
                    }
                }
            }
        }
    }
}
