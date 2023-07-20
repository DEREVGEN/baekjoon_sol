package com.backjoon.solution.problem_2294;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static public void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] DP = new int[K+1];
        for (int i = 1; i <= K; i++) // i는 1부터 K까지, DP의 그리티 특성상 K까지 모두 테이블화 시켜야함
                DP[i] = 10001;

        int[] coin = new int[N];

        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(input.readLine());

            for (int v = coin[i]; v <= K; v++) {
                DP[v] = Math.min(DP[v], DP[v-coin[i]] + 1);
            }
        }

        if (DP[K] == 10001)
            System.out.println(-1);
        else
            System.out.println(DP[K]);
    }
}
