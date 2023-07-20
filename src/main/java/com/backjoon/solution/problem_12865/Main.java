package com.backjoon.solution.problem_12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int N, M;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] backpack = new int[N+1][M+1];

        int[] W = new int[N+1]; // 물건 무게
        int[] V = new int[N+1]; // 물건 가치

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(input.readLine());

            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        int minW = W[0];

        if (minW > M) {
            System.out.println(0);
            return;
        }

        // 최소 물건 무게부터 시작.
        for (int i = 1; i <= N; i++) {
            if (W[i] < minW)
                minW = W[i];
        }

        // i : 총 물건 개수, w : 총 물건 무게
        for (int i = 1; i <= N; i++) {
            for (int w = minW; w <= M; w++) {
                if (w - W[i] >= 0) {
                    backpack[i][w] = Math.max(backpack[i-1][w-W[i]] + V[i], backpack[i-1][w]);
                } else {
                    backpack[i][w] = backpack[i-1][w];
                }
            }
        }

        System.out.println(backpack[N][M]);
    }
}
