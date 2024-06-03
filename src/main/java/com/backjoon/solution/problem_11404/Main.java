package com.backjoon.solution.problem_11404;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());
        int M = Integer.parseInt(input.readLine());

        final int INF = 10000000;

        int[][] cityCosts = new int[N+1][N+1];
        for (int[] cityCost : cityCosts)
            Arrays.fill(cityCost, INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            cityCosts[a][b] = Math.min(cityCosts[a][b], c);
        }

        int[][] gCosts = new int[N+1][N+1];
        for (int[] gCost : gCosts)
            Arrays.fill(gCost, INF);


        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) gCosts[i][j] = 0;
                else gCosts[i][j] = cityCosts[i][j];
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    gCosts[j][k] = Math.min(gCosts[j][k], gCosts[j][i] + gCosts[i][k]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(gCosts[i][j] == INF ? 0 : gCosts[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);

    }
}