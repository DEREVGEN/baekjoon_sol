package com.backjoon.solution.problem_1562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());

        long[][][] DP = new long[N+1][10][1 << 10];

        int fullFill = (1 << 10) - 1;

        for (int i = 1; i <= 9; i++) {
            DP[1][i][1 << i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {

                for (int k = 0; k <= fullFill; k++) {

                    if (j == 0)
                        DP[i][0][k | (1 << 0)] += DP[i - 1][1][k] % 1000000000;
                    else if (j == 9)
                        DP[i][9][k | (1 << 9)] += DP[i - 1][8][k] % 1000000000;
                    else {
                        DP[i][j][k | (1 << j)] += (DP[i - 1][j - 1][k] + DP[i - 1][j + 1][k]) % 1000000000;
                    }

                }
            }
        }

        long sum = 0;
        for (int i = 0; i <= 9; i++)
            sum += DP[N][i][fullFill];

        System.out.println(sum % 1000000000);
    }
}
