package com.backjoon.solution.problem_2629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        st = new StringTokenizer(input.readLine());

        int[] w1 = new int[N+1];
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            w1[i] = Integer.parseInt(st.nextToken());
            sum += w1[i];
        }

        int M = Integer.parseInt(input.readLine());

        st = new StringTokenizer(input.readLine());

        int[] w2 = new int[M+1];
        for (int i = 1; i <= M; i++)
            w2[i] = Integer.parseInt(st.nextToken());

        boolean[] dp = new boolean[40001];
        dp[0] = true;

        for (int i = 1; i <= N; i++) {
            for (int j = sum; j >= 0; j--) {
                if (dp[j])
                    dp[j + w1[i]] = true;
            }
            for (int j = 0; j <= sum; j++) {
                if (dp[j])
                    dp[Math.abs(j - w1[i])] = true;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int m = 1; m <= M; m++) {
            if (dp[w2[m]])
                sb.append("Y ");
            else
                sb.append("N ");
        }

        System.out.println(sb);

    }
}