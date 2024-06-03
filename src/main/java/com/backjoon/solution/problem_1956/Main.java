package com.backjoon.solution.problem_1956;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] cityCosts = new int[V+1][V+1];

        final int INF = 100000000;

        for (int[] cityCost : cityCosts) Arrays.fill(cityCost, INF);

        for (int i = 1; i <= V; i++)
            cityCosts[i][i] = 0;

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(input.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            cityCosts[a][b] = c;
        }

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                for (int k = 1; k <= V; k++) {
                    cityCosts[j][k] = Math.min(cityCosts[j][k], cityCosts[j][i] + cityCosts[i][k]);
                }
            }
        }

        int min = INF;

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) continue;
                min = Math.min(min, cityCosts[i][j] + cityCosts[j][i]);
            }
        }

        System.out.println(min == INF ? -1 : min);
    }
}
