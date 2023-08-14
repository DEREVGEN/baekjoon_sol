package com.backjoon.solution.problem_1315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] strength;
    static int[] intellect;
    static int[] pnt;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());

        strength = new int[N];
        intellect = new int[N];
        pnt = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            strength[i] = Integer.parseInt(st.nextToken());
            intellect[i] = Integer.parseInt(st.nextToken());
            pnt[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[1001][1001];
        for (int i = 0; i <= 1000; i++) // -1로 초기화
            Arrays.fill(dp[i], -1);


        System.out.println(findQuests(1, 1, 0));
    }

    public static int findQuests(int I, int J, int Ds) {

        if (dp[I][J] != -1)
            return dp[I][J];

        int sumStat = 0, cnt = 0;
        boolean[] clear = new boolean[N];

        int Es = -Ds;

        for (int i = 0; i < N; i++) {
            if (strength[i] <= I || intellect[i] <= J) {
                Es += pnt[i];
                cnt++;
                clear[i] = true;
            }
        }

        int max = 0;

        for (int i = 0; i < N; i++) {
            if (!clear[i]) {
                if (strength[i] - I <= Es)
                    max = Math.max(max, findQuests(strength[i], J, strength[i] + J -2));
                if (intellect[i] - J <= Es)
                    max = Math.max(max, findQuests(I, intellect[i], intellect[i] + I -2));
            }
        }

        if (max == 0) // 없을때
            return cnt;

        return dp[I][J] = max;
    }
}


