package com.backjoon.solution.problem_1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int[][] ways;
    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        ways = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                ways[i][j] = -1;
            }
        }

        int result = findWayDFS(N-1, M-1);

        System.out.println(result);
    }

    public static int findWayDFS(int y, int x) {
        if (y == 0 && x == 0)
            return 1;

        if (ways[y][x] == -1) {
            ways[y][x] = 0;

            for (int d = 0; d < 4; d++) {
                int my = y + direction[d][0];
                int mx = x + direction[d][1];

                if (my >= N || my < 0 || mx >= M || mx < 0)
                    continue;

                if (ways[my][mx] != 0 && map[y][x] < map[my][mx]) {
                    ways[y][x] += findWayDFS(my, mx);
                }
            }
        }

        return ways[y][x];
    }
}
