package com.backjoon.solution.problem_1328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, L, R;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        long[][][] buildings = new long[N+1][L+1][R+1];
        buildings[1][1][1] = 1;

        for (int n = 2; n <= N; n++) {
            for (int i = 1; i <= L; i++) {
                for (int j = 1; j <= R; j++) {
                    buildings[n][i][j] = (int) (((long)buildings[n - 1][i - 1][j] + buildings[n - 1][i][j - 1] + buildings[n - 1][i][j] * (n - 2)) % 1000000007);
                }
            }
        }

        System.out.println(buildings[N][L][R]);
    }
}
