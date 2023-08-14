package com.backjoon.solution.problem_1514;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Main {

    static int N;
    static int[] correctPwd;
    static int[] pwd;
    static int[][][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());

        pwd = new int[N+4];
        correctPwd = new int[N+4];

        dp = new int[N+4][10][10][10];
        for (int i = 1; i <= N; i++)
            for (int j = 0; j < 10; j++)
                for (int k = 0; k < 10; k++)
                        Arrays.fill(dp[i][j][k], -1);

        String givenNums = input.readLine();
        String givenCorrectNums = input.readLine();
        for (int i = 0; i < givenNums.length(); i++) {
            pwd[i+1] = Integer.parseInt(givenNums.substring(i, i+1));
            correctPwd[i+1] = Integer.parseInt(givenCorrectNums.substring(i, i+1));
        }


        System.out.println(findPwd(1, pwd[1], pwd[2], pwd[3]));
    }

    public static int findPwd(int cnt, int a, int b, int c) {

        if (cnt > N)
            return 0;

        if (dp[cnt][a][b][c] != -1)
            return dp[cnt][a][b][c];

        dp[cnt][a][b][c] = Integer.MAX_VALUE;

        int gap = (correctPwd[cnt] - a + 10) % 10;

        int[] d = {gap, 10-gap};

        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= d[i]; j++) {
                for (int k = 0; k <= j; k++) {

                    int bb = (b + ((i == 0) ? j : -j) + 10) % 10;
                    int cc = (c + ((i == 0) ? k : -k) + 10) % 10;

                    int res = findPwd(cnt + 1, bb, cc, pwd[cnt+3]);
                    res += (d[i] - j + 2) / 3 + (j - k + 2) / 3 + (k + 2) / 3;
                    dp[cnt][a][b][c] = Math.min(dp[cnt][a][b][c], res);
                }
            }
        }

        return dp[cnt][a][b][c];
    }
}
