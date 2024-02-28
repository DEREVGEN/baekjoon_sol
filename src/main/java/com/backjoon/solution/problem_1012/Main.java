package com.backjoon.solution.problem_1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] map;
    static boolean[][] isVisit;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(input.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            map = new boolean[N][M];
            isVisit = new boolean[N][M];

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(input.readLine());

                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                map[y][x] = true;
            }

            int cnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!map[i][j] || isVisit[i][j]) continue;
                    cnt++;
                    bfs(i, j);
                }
            }

            System.out.println(cnt);
        }
    }

    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void bfs(int startY, int startX) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startY, startX});
        isVisit[startY][startX] = true;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int d = 0; d < 4; d++) {
                int movedY = node[0] + direction[d][0];
                int movedX = node[1] + direction[d][1];

                if (0 > movedY || movedY >= N || 0 > movedX || movedX >= M || isVisit[movedY][movedX] || !map[movedY][movedX]) continue;

                isVisit[movedY][movedX] = true;
                queue.add(new int[]{movedY, movedX});
            }
        }
    }
}