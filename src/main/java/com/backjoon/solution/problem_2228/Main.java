package com.backjoon.solution.problem_2228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, M;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] sums = new int[N+1];

        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(input.readLine());
            sums[i] = num + sums[i-1];
        }

        int[][] DP = new int[M+1][N+1];

        for (int i = 1; i <= M; i++) {
            DP[i][0] = -3276800;
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                DP[i][j] = DP[i][j-1];

                for (int k = 1; k <= j; k++) {
                    if (k >= 2)
                        DP[i][j] = Math.max(DP[i][j], DP[i-1][k-2] + sums[j] - sums[k - 1]);
                    else if (k == 1 && i == 1)
                        DP[i][j] = Math.max(DP[i][j], sums[j]);
                }
            }
        }

/*        for (int i = 0; i <= M; i++) {
            for (int j = 0; j <= N; j++) {
                System.out.printf("%10d ", DP[i][j]);
            }
            System.out.println();
        }*/

        System.out.println(DP[M][N]);
    }
}
